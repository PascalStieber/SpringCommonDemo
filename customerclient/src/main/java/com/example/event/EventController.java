package com.example.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

	public static Logger logger = LoggerFactory.getLogger(EventController.class);
	// private final CountDownLatch latch = new CountDownLatch(3);

	@Autowired
	private KafkaTemplate<String, String> template;

	@RequestMapping("/publishEvent")
	public String publishEvent() {
		this.template.send("eventtopic", "foo1");
		this.template.send("eventtopic", "foo2");
		this.template.send("eventtopic", "foo3");
		// latch.await(60, TimeUnit.SECONDS);
		return "Messaging proceeded...";
	}

	@KafkaListener(topics = "eventtopic")
	public void listen(String pMessage) {
		System.out.println("Listener received: " + pMessage);
	}


	// @Autowired
	// SimpleSourceBean simpleSourceBean;
	//
	// @RequestMapping("/publishEvent")
	// public ModelAndView publishEvent() {
	// simpleSourceBean.publishEvent();
	// return new ModelAndView("index");
	// }
}