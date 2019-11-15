package com.aem.training.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.day.cq.wcm.api.Page;

@Model(adaptables=SlingHttpServletRequest.class) 
public class Slinghttprequestmodel {
	// Injects currentPage using ScriptVariable annotation
    @ScriptVariable(name="currentPage")
    @Optional
    Page page;

	public String getCurrentPage() {
		//return page.getTitle();
		return "hai";
	}

   
}
