package com.assigment.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class Application {

	@RestController
	@RequestMapping("/api/v1")
	static class CustomerController {
		ArrayList<Customer> customers = new ArrayList<>();

		public CustomerController() {
			//initialize data to array list
			customers.add(new Customer(1L, "senglorn", "M", 22, "PP"));
			customers.add(new Customer(2L, "Maneath", "F", 21, "KPC"));
			customers.add(new Customer(3L, "Chanleaph", "M", 23, "TK"));
			customers.add(new Customer(4L, "Srey Nak", "M", 20, "PP"));

		}

		@GetMapping("/customers")
		public ResponseEntity<?> getCustomersData() {
			return ResponseEntity.ok(new CustomerResponse< ArrayList<Customer>>(
					LocalDateTime.now(),
					200,
					"You get all customer Successfully",
					customers
			));
		}

		// Insert data in array
		@PostMapping("/customers")
		public ResponseEntity<?> insertNewCustomers(@RequestBody Customer newCustomerInsert) {
			customers.add(newCustomerInsert);
			return ResponseEntity.ok(new CustomerResponse<>(
					LocalDateTime.now(),
					200,
					"You get all customer Successfully",
					newCustomerInsert
			));
		}

		// Get data by ID
		@GetMapping("/customers/{id}")
		public ResponseEntity<?> getCustomerById(@PathVariable("id") long cusId) {
			boolean x = false;
			for (Customer cus : customers) {
				if (cus.getId() == cusId) {
					x = true;
					return ResponseEntity.ok(new CustomerResponse<>(
							LocalDateTime.now(),
							200,
							"Record that search by id has found Successfully",
							cus
					));
				}
			}
			if (!x) {
				return new ResponseEntity<>(
						"This record not found",HttpStatus.BAD_REQUEST
				);
			}
			return null;
		}

		// Find Customer by Name
		@GetMapping("/customer/search")
		public ResponseEntity<?> findCustomerByName(@RequestParam String name) {
			boolean x = false;
			for (Customer cus : customers) {
				x = true;
				if (cus.getName().equals(name)) {
					return ResponseEntity.ok(new CustomerResponse<>(
							LocalDateTime.now(),
							200,
							"Record that search by id has found Successfully",
							cus
					));
				}
			}
			if (!x) {
				return new ResponseEntity<>(
						"This Name not Found",HttpStatus.BAD_REQUEST
				);
			}
			return null;
		}

		//Update Date
		@PutMapping("/customer/update/{id}")
		public ResponseEntity<?> updateNewCustomerById(@PathVariable("id") long id, @RequestBody Customer customerUpdate) {
			for (int i = 0; i < customers.size(); i++) {
				if (customers.get(i).getId() == id) {
					Customer customer = new Customer();
					customer.setId(id);
					customers.get(i).setName(customerUpdate.getName());
					customers.get(i).setGender(customerUpdate.getGender());
					customers.get(i).setAge(customerUpdate.getAge());
					customers.get(i).setAddress(customerUpdate.getAddress());
				}
			}
			return ResponseEntity.ok(new CustomerResponse<>(
					LocalDateTime.now(),
					200,
					"You update customer Successfully",
					customerUpdate
			));
		}


		@DeleteMapping("/customer/delete/{id}")
		public ResponseEntity<?> deleteStaff(@PathVariable("id") long id) {
			boolean x = false;
			for (int i = 0; i < customers.size(); i++) {
				x = true;
				if (customers.get(i).getId() == id) {
					Customer customer = new Customer();
					customers.remove(i);
					return ResponseEntity.ok(new CustomerResponse<>(
							LocalDateTime.now(),
							200,
							"You delete customer Successfully",
							customer
					));
				}
			}
			if (!x) {
				return new ResponseEntity<>(
						"Can't not Find",HttpStatus.BAD_REQUEST
				);
			}
			return null;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
