package com.example.event;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(Source.class)
public class SimpleSourceBean {
	private Source source;
	private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

	@Autowired
	public SimpleSourceBean(Source source) {
		this.source = source;
	}

	public void publishEvent() {
		logger.info("Sending Kafka message...");
		// source.output().send(MessageBuilder.withPayload(c).build());
		source.output().send(new GenericMessage<String>("Hallo Welt"));
	}

}