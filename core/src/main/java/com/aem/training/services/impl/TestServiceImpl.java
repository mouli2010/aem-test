package com.aem.training.services.impl;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.aem.training.services.TestConfigurations;
import com.aem.training.services.TestService;
import com.day.cq.wcm.api.components.ComponentContext;
@Component(service=TestService.class,immediate=true,
property= {
		Constants.SERVICE_VENDOR +"= Regnant software solutions",
		Constants.SERVICE_DESCRIPTION +"=This is sample Description."
})
@Designate(ocd=TestConfigurations.class)
public class TestServiceImpl implements TestService {

	private String message;
	@Override
	public String getMessage() {
		return "test message"+message;

	}
 
	@Override
	public String getMessage(String message) {
		return message;
	}
	@Activate
	protected void activate(TestConfigurations testConfigurations) {
		message = testConfigurations.getMessage();
	}

}
