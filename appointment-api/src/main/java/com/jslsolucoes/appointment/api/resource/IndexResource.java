package com.jslsolucoes.appointment.api.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexResource {

	@GetMapping
	public IndexResponse index() {
		return new IndexResponse("its up and running");
	}

	static class IndexResponse {
		private final String message;

		public IndexResponse(String message) {
			this.message = message;
		}

		public String message() {
			return message;
		}
	}

}
