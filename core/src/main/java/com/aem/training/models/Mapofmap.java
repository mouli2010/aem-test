package com.aem.training.models;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class Mapofmap {
	public HashMap<String, HashMap> map = new HashMap<String, HashMap>();

	public HashMap<String, HashMap> getMap() {
		return map;
	}

	@PostConstruct
	public void activate() {
		HashMap m1 = new HashMap<>();
		m1.put("aem", "6.1");
		m1.put("aem6", "6.2");
		map.put("a", m1 );
		HashMap m2 = new HashMap<>();
		m2.put("cq", "5.5");
		m2.put("cq5", "5.4");
		map.put("b", m2);
	}


}
