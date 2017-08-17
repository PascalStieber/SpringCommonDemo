package com.example.web;

import java.util.ArrayList;
import java.util.List;

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

//@Configuration
//@EnableWebMvc
//@RestController
@Controller
public class WebController {

	Logger log = LoggerFactory.getLogger(ClientApplication.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Value("${example.property:default}")
	private String exampleProperty;

	@RequestMapping("/")
	public ModelAndView index() {
		System.out.println(exampleProperty);
		return new ModelAndView("index");
	}

	@RequestMapping("/securedPage")
	public ModelAndView securedPage() {
		return new ModelAndView("securedPage");
	}

	@RequestMapping("/securedPage2")
	public ModelAndView securedPage2() {
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
	public void getCustomers() {
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
		System.out.println(customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : customerRepository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

	// @Override
	// public void configureDefaultServletHandling(final
	// DefaultServletHandlerConfigurer configurer) {
	// configurer.enable();
	// }
	//
	// @Override
	// public void addViewControllers(final ViewControllerRegistry registry) {
	// super.addViewControllers(registry);
	// registry.addViewController("/").setViewName("forward:/index");
	// registry.addViewController("/index");
	// registry.addViewController("/securedPage");
	// registry.addViewController("/securedPage2");
	// }
	//
	// @Override
	// public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	// }

}
