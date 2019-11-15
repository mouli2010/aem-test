package com.aem.training.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;

import com.day.cq.wcm.api.designer.Style;

@Model(adaptables=Resource.class)
public class Designdialogvalue {
	
	public String text1;
	
	@Inject @Source("script-bindings")
	private  Style currentStyle;
	
	@PostConstruct
	private void init() {
		
		text1=currentStyle.get("text",String.class);
	 System.out.println("design");
	}
	public String getText1() {
		return  text1;
	}
}
