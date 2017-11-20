package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Contract {

    @Id
    public String id;
    public String reference;
    public String subject;

    public Contract() {}

    public Contract(String reference, String subject) {
        this.reference = reference;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return String.format(
                "Contract[id=%s, reference='%s', subject='%s']",
                id, reference, subject);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}