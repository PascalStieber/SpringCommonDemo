package com.example.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ClientApplication;
import com.example.entity.Contract;
import com.example.entity.Customer;
import com.example.repository.ContractRepository;
import com.example.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class WebController {

	Logger log = LoggerFactory.getLogger(ClientApplication.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Value("${exampleproperty:default}")
	private String exampleProperty;

	@RequestMapping("/")
	public ModelAndView index(HttpServletResponse response) {
		System.out.println(this.exampleProperty);
		return new ModelAndView("index");
	}

	@RequestMapping("/securedPage")
	public ModelAndView securedPage() {
	
		return new ModelAndView("securedPage");
	}

	@RequestMapping("/hystrixtest")
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public void hystrixtest() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void fallbackMethod() {
		System.out.println("Hystrix hook: running fallback() method due to long running thread.");
	}

	@RequestMapping("/customers")
	public ModelAndView getCustomers() {
		customerRepository.deleteAll();
		contractRepository.deleteAll();
		Contract contract = new Contract("referenceAZ23B", "quotation");
		Contract savedContract = contractRepository.save(contract);

		List<Contract> contractList = new ArrayList<Contract>();
		contractList.add(savedContract);
		// save a couple of customers
		customerRepository.save(new Customer("Alice", "Smith", contractList));
		customerRepository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
			if (customer.getContracts() != null) {
				for (Contract lContract : customer.getContracts()) {
					System.out.println("With Contract: " + lContract);
				}
			}
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstname("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : customerRepository.findByLastname("Smith")) {
			System.out.println(customer);
		}
		
		ModelAndView model = new ModelAndView("customers");
		model.addObject("customerList", customerRepository.findAll());
		return model;
	}

}
