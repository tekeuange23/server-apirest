package com.client.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.client.apirest.model.collection.ClientCollection;
import com.client.apirest.model.entity.Client;

@SpringBootTest
class RangerClientProjectApplicationTests {

	@Autowired
	ClientCollection clientService = new ClientCollection();

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		Client client = new Client();
		client.setIdNumber(Long.parseLong("2001014800086"));
		client.setFirstName("Tekeu");
		client.setLastName("Ange");
		client.setMobileNumber("0000000000");
		client.setPhysicalAddress("WORLD DEVE");
		
		try {
			this.clientService.save(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Getting the client
		assertNotNull(this.clientService.findByIdNumber(client.getIdNumber()));
		assertNotNull(this.clientService.findByMobileNumber(client.getMobileNumber()));
		assertNotNull(this.clientService.findByFirstName(client.getFirstName()));
		assertNotEquals(this.clientService.findByFirstName(client.getFirstName()), new ArrayList<Client>());

		assertThat(this.clientService.getClientList().values()).size().isGreaterThan(0);
	}
	
	@Test
	public void testUpdate() {
		
		// UPDATE HIM
		Client newClient = new Client();
		newClient.setIdNumber((long) Long.parseLong("9401044800085"));
		newClient.setFirstName("222");
		newClient.setLastName("222");
		newClient.setMobileNumber("2222222222");
		newClient.setPhysicalAddress("222");
		
		Client updatedClient = null;
		try {
			updatedClient = this.clientService.update(newClient, Long.parseLong("2001014800086"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// check if it the global collection just have one element
		System.out.println(this.clientService.getClientList().values().size());
		assertThat(this.clientService.getClientList().values()).size().isEqualTo(1);
	}

}
