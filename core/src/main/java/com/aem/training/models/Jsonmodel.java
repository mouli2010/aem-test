package com.aem.training.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.aem.training.servlets.UrlFromServlet;

public class Jsonmodel {
	@Inject
	private UrlFromServlet url;
	private Map<String,String> map=new HashMap<String, String>();;

	public Map<String,String> getMap() {
	  return map;
	 }
      
	
	
}
