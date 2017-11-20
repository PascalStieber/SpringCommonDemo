package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventService {
	
	@Autowired
	SimpleSourceBean simpleSourceBean;

	@RequestMapping("/test")
	public ModelAndView saveOrg() {
		simpleSourceBean.publishCustomerChange("pascal");
		return new ModelAndView("index");
	}
}