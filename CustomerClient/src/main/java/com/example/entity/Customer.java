package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

	@Id
	public String id;
	public String firstname;
	public String lastname;
	public List<Contract> contracts;

	public Customer() {
	}

	public Customer(String firstname, String lastname, List<Contract> contracts) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.contracts = contracts;
	}

	public Customer(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstname='%s', lastname='%s']", id, firstname, lastname);
	}

	public void addContract(Contract pContract) {
		if (this.getContracts() == null) {
			setContracts(new ArrayList<Contract>());
		}
		this.getContracts().add(pContract);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

}