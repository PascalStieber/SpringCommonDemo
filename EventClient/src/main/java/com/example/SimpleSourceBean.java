package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.example.SimpleSourceBean.Customer;

@Component
public class SimpleSourceBean {
	private Source source;

	private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

	@Autowired
	public SimpleSourceBean(Source source) {
		this.source = source;
	}

	public void publishCustomerChange(String name) {
		logger.info("Sending Kafka message {} for Organization Id: {}");
		Customer c = new Customer();
		c.setName(name);
//		source.output().send(MessageBuilder.withPayload(c).build());
		
		source.output().send(new GenericMessage<String>("Hallo Welt"));
	}

	public class Customer {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String mCustomer) {
			this.name = mCustomer;
		}
	}

}