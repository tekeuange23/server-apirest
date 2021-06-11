package com.client.apirest.model.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.client.apirest.model.entity.Client;

@Service
public class ClientCollection {

	private Map<Long, Client> clientList;

	public ClientCollection() {
		this.clientList = new HashMap<Long, Client>();
	}

	/**
	 **************************************************************************
	 ***************************** GETTERS AND SETTERS ************************
	 ************************************************************************** 
	 **/
	public Map<Long, Client> getClientList() {
		return clientList;
	}

	public void setClientList(Map<Long, Client> clientList) {
		this.clientList = clientList;
	}

	/**
	 **************************************************************************
	 ***************************** METHODS ************************************
	 ************************************************************************** 
	 **/
	public Collection<Client> findAll() {
		return this.clientList.values();
	}

	public Collection<Client> findByFirstName(String name) {

		Collection<Client> clientsList = new ArrayList<Client>();
		Iterator<Entry<Long, Client>> iterator = this.clientList.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Long, Client> currentClient = (Map.Entry<Long, Client>) iterator.next();
			if (currentClient.getValue().getFirstName().equals(name)) {
				clientsList.add(currentClient.getValue());
			}
		}
		return clientsList;
	}

	public Client findByIdNumber(Long idNumber) {
		// Iterator<Entry<Long, Client>> iterator =
		// this.clientList.entrySet().iterator();
		// while (iterator.hasNext()) {
		// Map.Entry<Long, Client> currentClient = (Map.Entry<Long, Client>)
		// iterator.next();
		// if (currentClient.getValue().getIdNumber().equals(idNumber)) {
		// return currentClient.getValue();
		// }
		// }
		// return null;
		return this.clientList.get(idNumber);
	}

	public Client findByMobileNumber(String mobileNumber) {
		Iterator<Entry<Long, Client>> iterator = this.clientList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Long, Client> currentClient = (Map.Entry<Long, Client>) iterator.next();
			if (currentClient.getValue().getMobileNumber().equals(mobileNumber)) {
				return currentClient.getValue();
			}
		}
		return null;
	}

	public Client save(Client client) throws Exception {
		if (!this.isIdNumberValid(client)) {
			throw new Exception("The entered ID number is not a valid one.");
		}
		this.areIdNumberAndPhoneNumberUnique(client);
		this.clientList.put(client.getIdNumber(), client);
		return client;
	}

	public Client update(Client client, Long idNumber) throws Exception {

		Client clientToUpdate = this.clientList.get((Long) idNumber);

		if (clientToUpdate == null) {
			throw new Exception("User with ID Number " + idNumber + " doesn't exist");
		} else if (!this.isIdNumberValid(client)) {
			throw new Exception("The entered ID number is not a valid one.");
		}

		this.areIdNumberAndPhoneNumberUnique(client);
		this.clientList.put(idNumber, client);
		return client;
	}

	/**
	 **************************************************************************
	 ***************************** VALIDATORS *********************************
	 ************************************************************************** 
	 **/
	public void areIdNumberAndPhoneNumberUnique(Client client) throws Exception {
		Iterator<Entry<Long, Client>> iterator = this.clientList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Long, Client> currentClient = (Map.Entry<Long, Client>) iterator.next();
			if (currentClient.getValue().getIdNumber().equals((Long) client.getIdNumber())) {
				throw new Exception("This action cannot be performed due to unique constraint on ID Number.");
			} else if (currentClient.getValue().getMobileNumber().equals(client.getMobileNumber())) {
				throw new Exception("This action cannot be performed due to unique constraint on MOBILE Number.");
			}
		}
	}

	public Boolean isIdNumberValid(Client client) throws Exception {
		String regExpression = "([0-9][0-9])((?:[0][1-9])|(?:[1][0-2]))((?:[0-2][0-9])|(?:[3][0-1]))([0-9])([0-9]{3})([0-9])([0-9])([0-9])";
		Pattern pattern = Pattern.compile(regExpression);
		String idNumber = client.getIdNumber().toString();
		Matcher matcher = pattern.matcher(idNumber);

		if (matcher.matches()) {

			int tot1 = 0;
			for (int i = 0; i < idNumber.length() - 1; i += 2) {
				tot1 = tot1 + Integer.parseInt(idNumber.substring(i, i + 1));
			}

			StringBuffer field1 = new StringBuffer("");
			for (int i = 1; i < idNumber.length(); i += 2) {
				field1.append(idNumber.substring(i, i + 1));
			}

			String evenTotStr = (Long.parseLong(field1.toString()) * 2) + "";
			int tot2 = 0;
			for (int i = 0; i < evenTotStr.length(); i++) {
				tot2 = tot2 + Integer.parseInt(evenTotStr.substring(i, i + 1));
			}

			int lastD = (10 - ((tot1 + tot2) % 10)) % 10;
			int checkD = Integer.parseInt(idNumber.substring(12, 13));

			if (checkD == lastD) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
