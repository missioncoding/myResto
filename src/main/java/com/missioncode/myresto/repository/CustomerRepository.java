package com.missioncode.myresto.repository;

import com.missioncode.myresto.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    List<Customer> findByFullname(String name);
    List<Customer> findByEmail(String email);
    List<Customer> findByPhone(String phone);
}
