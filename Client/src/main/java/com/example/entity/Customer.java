package com.example.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public List<Contract> contracts;
    
    public Customer() {}

    public Customer(String firstName, String lastName, List<Contract> contracts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contracts = contracts;
    }
    
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
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

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

    
    
}