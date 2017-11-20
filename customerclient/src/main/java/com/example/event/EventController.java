package com.example.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {
	
	@Autowired
	SimpleSourceBean simpleSourceBean;

	@RequestMapping("/publishEvent")
	public ModelAndView publishEvent() {
		simpleSourceBean.publishEvent();
		return new ModelAndView("index");
	}
}