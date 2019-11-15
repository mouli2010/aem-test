package com.aem.training.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import com.aem.training.services.Modelinterface;
import com.aem.training.services.Modelinterface2;
@Model(adaptables=Resource.class,adapters=Modelinterface.class)
public class InterfaceModel extends DemoClass implements Modelinterface,Modelinterface2 {

	public String getName() {
		// TODO Auto-generated method stub
		return "Sucess";
	}
	public String getPath() {
		// TODO Auto-generated method stub
		return "hello path";
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "hello hyderabad";
	}

}
