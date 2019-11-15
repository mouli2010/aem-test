package com.aem.training.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables=Resource.class)
public class Navigation {
	@Inject
	@Optional
	private  Resource navItems;
	private List<MultifieldBean> navigationItems;
	
public List<MultifieldBean> getNavigationItems() {
		return navigationItems;
	}
@PostConstruct
private void init() {
	if(null!= navItems && navItems.hasChildren() )
	{
		navigationItems =new ArrayList<>();
		Iterator<Resource>listChildren=navItems.listChildren();
		while(listChildren.hasNext())
		{
			MultifieldBean bean=new MultifieldBean();
			Resource resource=listChildren.next();
			ValueMap valueMap= resource.getValueMap();
		bean.setTitle(valueMap.get("linkTitle", String.class));
		bean.setLink(valueMap.get("linkURL", String.class));
		navigationItems.add(bean);
		
		}
	}
	
}
public class MultifieldBean{
	private String title;
	private String link;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
}
