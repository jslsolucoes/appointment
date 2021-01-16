package com.jslsolucoes.appointment.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslsolucoes.appointment.api.domain.Customer;
import com.jslsolucoes.appointment.api.repo.CustomerRepo;

@Service
public class CustomerUseCase {

	private CustomerRepo customerRepo;

	@Autowired
	public CustomerUseCase(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	public Customer createNewOrGetExistent(String name, String email, Long phone) {
		return customerRepo.findByEmail(email).orElseGet(() -> newCustomer(name, email, phone));
	}

	private Customer newCustomer(String name, String email, Long phone) {
		Customer customer = Customer.Builder.newBuilder().withName(name).withEmail(email).withPhone(phone).build();
		return customerRepo.save(customer);
	}
}
