package com.jslsolucoes.appointment.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "customer_sq", allocationSize = 1, initialValue = 1, sequenceName = "customer_sq")
public class Customer {

	@Id
	@GeneratedValue(generator = "customer_sq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	private String email;

	private Long phone;

	public Customer() {

	}

	public Customer(String name, String email, Long phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public String email() {
		return email;
	}

	public Long phone() {
		return phone;
	}

	public static class Builder {

		private String name;
		private String email;
		private Long phone;

		private Builder() {

		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withPhone(Long phone) {
			this.phone = phone;
			return this;
		}

		public Customer build() {
			return new Customer(name, email, phone);
		}
	}

}
