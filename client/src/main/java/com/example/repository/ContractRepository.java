package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.Contract;
import com.example.entity.Customer;

public interface ContractRepository extends MongoRepository<Contract, String> {

    public Customer findByReference(String reference);
    public List<Contract> findBySubject(String subject);

}
