package com.bookstore.service;

import com.bookstore.model.Customer;
import com.bookstore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Integer customerId, Customer customer) {
        customer.setCustomerId(customerId);
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
}

