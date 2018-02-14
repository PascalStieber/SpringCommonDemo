package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSecurityController {

	// Login form
	@RequestMapping("/login.html")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	// Login form with error
	@RequestMapping("/login-error.html")
	public ModelAndView loginError(Model model) {
		model.addAttribute("loginError", true);
		ModelAndView loginErrorView = new ModelAndView("login");
		loginErrorView.addObject("loginError", true);
		return loginErrorView;
	}

}
