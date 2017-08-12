package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	@Autowired
	SimpleSourceBean simpleSourceBean;

	public void saveOrg() {
		simpleSourceBean.publishOrgChange();
	}
}