package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//@RestController
@Controller
public class WebController {

	Logger log = LoggerFactory.getLogger(ClientApplication.class);
	
	@RequestMapping("/securedPage")
	public ModelAndView securedPage(){
		return new ModelAndView("securedPage");
	}
	
	@Value("${example.property:default}")
	private String exampleProperty;
	
	@RequestMapping("/securedPage2")
	public ModelAndView securedPage2(){
		return new ModelAndView("securedPage");
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
	    System.out.println(exampleProperty);
		return new ModelAndView("index");
	}




//	@Override
//	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}
//
//	@Override
//	public void addViewControllers(final ViewControllerRegistry registry) {
//		super.addViewControllers(registry);
//		registry.addViewController("/").setViewName("forward:/index");
//		registry.addViewController("/index");
//		registry.addViewController("/securedPage");
//		registry.addViewController("/securedPage2");
//	}
//
//	@Override
//	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}

}
