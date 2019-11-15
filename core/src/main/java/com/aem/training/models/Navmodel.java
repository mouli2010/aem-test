package com.aem.training.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class)
public class Navmodel {

	@Inject
	@Optional
	private String homeURL;
	@Inject
	@Optional
	private String productURL;
	@Inject
	@Optional
	private String blogURL;
	@Inject
	@Optional
	private String aboutURL;

	
	
	
	public String getHomeURL() {
		return homeURL;
	}

	public String getProductURL() {
		return productURL;
	}

	public String getBlogURL() {
		return blogURL;
	}

	public String getContactURL() {
		return aboutURL;
	}

	@Inject
	private ResourceResolver resolver1;

	

	public List<Page> getHomeitems() {
		return homeitems;
	}

	

	public List<Page> getProductitems() {
		return productitems;
	}

	

	public List<Page> getBlogitems() {
		return blogitems;
	}

	

	public List<Page> getAboutitems() {
		return aboutitems;
	}

	
	private List<Page> homeitems = new ArrayList<>();
	private List<Page> productitems = new ArrayList<>();
	private List<Page> blogitems = new ArrayList<>();
	private List<Page> aboutitems = new ArrayList<>();
	

	/**
	 * @return list of child pages //
	 */

	@PostConstruct

	private void init() {
         
		 getlinks(resolver1, homeURL,homeitems);
		 getlinks(resolver1, productURL,productitems);
		 getlinks(resolver1, blogURL,blogitems);
		getlinks(resolver1, aboutURL,aboutitems);
	

	}

	public List<Page> getlinks(ResourceResolver resolver1, String property,List<Page> items) {
		
		if (null != property) {
			//System.out.println("two");
			//System.out.println(property);
			PageManager pageManager = resolver1.adaptTo(PageManager.class);

			if (null != pageManager) {
				Page page = pageManager.getPage(property);
				Iterator<Page> listChildren2 = page.listChildren();
				//Iterator<Page> listChildren2 = page.listChildren(null,true);
				while (listChildren2.hasNext()) {
					Page childPage = listChildren2.next();
					System.out.println("childPage=" + childPage.getTitle());
					items.add(childPage);
				}
			}
			 
		}
		System.out.println("---------------"); 
		return items;
		

	}

}
