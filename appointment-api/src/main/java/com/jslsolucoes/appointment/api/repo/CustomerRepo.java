package com.jslsolucoes.appointment.api.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jslsolucoes.appointment.api.domain.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

	public Optional<Customer> findByEmail(String email);

}
