 package com.aem.training.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * @author RAJAMOULI D
 *
 */
@Model(adaptables=Resource.class)
public class NavigationRootPage {
	@Inject
	@Optional
	private String linkURL;
	@Inject
	private ResourceResolver resolver;
	
	private List<Page> navItems;
	
	/**
	 * @return list of child pages
	 */
	public List<Page> getNavItems() {
		return navItems;
	}

	@PostConstruct
	private void init() {
		 if(null!=linkURL) {
			 PageManager pageManager = resolver.adaptTo(PageManager.class);
			 navItems=new ArrayList<>();
			 if(null!=pageManager) {
				 Page page = pageManager.getPage(linkURL);
				 Iterator<Page> listChildren = page.listChildren();
				 while(listChildren.hasNext())
				 {
					 Page childPage = listChildren.next();
					 navItems.add(childPage);
				 }
			 }
			 
		 }
	}

}
