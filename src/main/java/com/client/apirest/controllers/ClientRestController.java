package com.client.apirest.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.client.apirest.model.collection.ClientCollection;
import com.client.apirest.model.entity.Client;

@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private ClientCollection clientService = new ClientCollection();

	@GetMapping("/clients")
	@ResponseStatus(HttpStatus.OK)
	public Collection<Client> index() {
		return this.clientService.findAll();
	}
	
	@GetMapping("/clients/id/{idNumber}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showByIdNumber(@PathVariable Long idNumber) {

		Client client = this.clientService.findByIdNumber(idNumber);
		Map<String, Object> response = new HashMap<>();

		if (client == null) {
			response.put("message", "Client ID: #".concat(idNumber.toString().concat(" doesn't exist.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("client", client);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@GetMapping("/clients/phone/{mobileNumber}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showByMobilePhone(@PathVariable String mobileNumber) {
		Client client = this.clientService.findByMobileNumber(mobileNumber);
		Map<String, Object> response = new HashMap<>();

		if (client == null) {
			response.put("message", "Client with mobile number ".concat(mobileNumber.toString().concat(" doesn't exist.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("client", client);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@GetMapping("/clients/name/{firstName}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showByFirstName(@PathVariable String firstName) {
		Collection<Client> clients = this.clientService.findByFirstName(firstName);
		Map<String, Object> response = new HashMap<>();

		if (clients.isEmpty()) {
			response.put("message", "Client named ".concat(firstName.toString().concat(" doesn't exist.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("clients", clients);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/clients")
	public ResponseEntity<?> create(@RequestBody Client client) {

		Client createdClient;
		Map<String, Object> response = new HashMap<>();
		try {
			createdClient = this.clientService.save(client);
		} catch (Exception e) {
			response.put("message", "Cannot create the user.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}

		response.put("message", "The client has been successfully created!");
		response.put("client", createdClient);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/clients/{idNumber}")
	public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long idNumber) {

		Client updatedClient;
		Map<String, Object> response = new HashMap<>();

		try {
			updatedClient = this.clientService.update(client, idNumber);
		} catch (Exception e) {
			response.put("message", "Cannot update the user.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}

		response.put("message", "The client has been successfully updated!");
		response.put("client", updatedClient);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
