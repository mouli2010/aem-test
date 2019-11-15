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
public class ImageWithText {
	@Inject
	@Optional
	private String itemtext ;
	public String getItemtext() {
		return itemtext;
	}
	@Inject
	@Optional
	private String itemtype ;
	public String getItemtype() {
		return itemtype;
	}
	@Inject
	@Optional
	private Resource  proItems;
	private List<MultifieldBean> promultiItems;
	
	public List<MultifieldBean> getPromultiItems() {
		return promultiItems;
	}
	@PostConstruct
	private void init() {
		
		if(null!=proItems && proItems.hasChildren())
		{
			promultiItems =new ArrayList<>();
			Iterator<Resource> listChildren = proItems.listChildren();
			while(listChildren.hasNext())
			{
				MultifieldBean bean=new MultifieldBean();
				Resource resource=listChildren.next();
				ValueMap valueMap = resource.getValueMap();
				bean.setImage(valueMap.get("linkimage", String.class));
				bean.setItemname(valueMap.get("itemname", String.class));
				promultiItems.add(bean);
				
			}
		}
		
	}
	
	
	public class MultifieldBean
	{
		
		private String image;
		private String  itemname;
		
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getItemname() {
			return itemname;
		}
		public void setItemname(String itemname) {
			this.itemname = itemname;
		}
		
	}
	

}
