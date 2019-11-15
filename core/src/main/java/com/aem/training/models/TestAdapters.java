package com.aem.training.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class TestAdapters {
	@Inject @Named("sling:resourceType")
	String type;

	public String getType() {
		return type;
	}

}
