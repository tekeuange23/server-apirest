package com.client.apirest.model.entity;

public class Client {

	private Long idNumber;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String physicalAddress;

	/**
	 **************************************************************************
	 ***************************** GETTERS AND SETTERS ************************
	 **************************************************************************	
	 **/
	public String getFirstName() {
		return firstName;
	}

	public Long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

}
