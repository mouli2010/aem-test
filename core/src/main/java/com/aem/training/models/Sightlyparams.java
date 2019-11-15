package com.aem.training.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class)
public class Sightlyparams {

	 @Inject
	 @Named("color")
	    String param;


	    public String getParam() {
	       return param;
	   }
	    @RequestAttribute(name="number")
	       int num;
	    public int getNum() {
	    	return num;
	    }
	 // Injects currentPage using ScriptVariable annotation
	    @ScriptVariable(name="currentPage")
	    Page page;

	    public String getPagePath() {
	        return  page.getPath();
	    }
	    
	    // Injects the child of the resource using ChildResource annotation
	    /*@ChildResource(name="productheader",injectionStrategy= InjectionStrategy.OPTIONAL,via = "resource")
	    Resource child;

	    public String getChildPath() {
	       return child.getPath();
	    }*/
	    // Injects title from ValuMap annotation using its attributes
	    /*@ValueMapValue(name = "title",via = "resource",injectionStrategy = InjectionStrategy.REQUIRED)
	     String pageTitle;

	     public String getTitle() {
	         return pageTitle;
	     }*/
}
