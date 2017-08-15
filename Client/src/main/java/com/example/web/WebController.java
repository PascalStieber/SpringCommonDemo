package com.example.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ClientApplication;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;

//@Configuration
//@EnableWebMvc
//@RestController
@Controller
public class WebController {

	Logger log = LoggerFactory.getLogger(ClientApplication.class);

	@Autowired
	private CustomerRepository repository;

	@Value("${example.property:default}")
	private String exampleProperty;

	@RequestMapping("/securedPage")
	public ModelAndView securedPage() {
		return new ModelAndView("securedPage");
	}

	@RequestMapping("/securedPage2")
	public ModelAndView securedPage2() {
		return new ModelAndView("securedPage");
	}

	@RequestMapping("/customers")
	public void getCustomers() {
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

	@RequestMapping("/")
	public ModelAndView index() {
		System.out.println(exampleProperty);
		return new ModelAndView("index");
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
