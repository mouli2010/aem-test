package com.aem.training.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import com.aem.training.services.TestService;

import javax.inject.Inject;

@Model(adaptables=Resource.class) 
public class ImageLink {
	@Inject
	@Optional
	private String fileReference;
	@Inject
	@Optional
	private String linkTitle;
	@Inject
	@Optional
	private String linkURL;
    @Inject
    private TestService testservice;
	public String getFileReference() {
		return fileReference;
	}
	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}
	public String getLinkTitle() {
		return linkTitle;
	}
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
	public String getLinkURL() {
		return linkURL;
	}
	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
	public String getMessage() {
		return  testservice.getMessage("message from model");  
	}

}
