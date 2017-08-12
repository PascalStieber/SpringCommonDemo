package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.MessageChannel;

@Component
public class SimpleSourceBean {
	private Source source;

	private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

	@Autowired
	public SimpleSourceBean(Source source) {
		this.source = source;
	}

	public void publishOrgChange() {
		logger.debug("Sending Kafka message {} for Organization Id: {}");
//		source.output()
		source.output().send(MessageBuilder.withPayload(new Customer()).build());
	}

	public class Customer {
		private String mCustomer;

		public String getmCustomer() {
			return mCustomer;
		}

		public void setmCustomer(String mCustomer) {
			this.mCustomer = mCustomer;
		}
	}

}