package com.aem.training.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
@Model(adaptables = Resource.class)
public class Mapiterator {
	private Map<Integer,String> map;

	public Map<Integer,String> getMap() {
	  return map;
	 }

	@PostConstruct
	    public void constructMap() {
	        map = new HashMap<Integer, String>();
	        map.put(1,"A");
	        map.put(2,"B");
	        System.out.println(map);
	    }
}
