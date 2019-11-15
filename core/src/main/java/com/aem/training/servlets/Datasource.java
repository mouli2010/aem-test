package com.aem.training.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class, property = { ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=aem/test",
		ServletResolverConstants.SLING_SERVLET_NAME + "=Datasource Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS + "=GET" })
public class Datasource extends SlingSafeMethodsServlet {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(Datasource.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		List<Resource> resourceList = new ArrayList<Resource>();
		ValueMap vm = null;

		ResourceResolver resolver = request.getResourceResolver();
		PageManager pageManager = resolver.adaptTo(PageManager.class);

		Page page = pageManager.getPage("/content/homepage");
		log.info("page.getTitle", page.getTitle());
		if (page != null) {
			Iterator<Page> listChildren = page.listChildren();
			while (listChildren.hasNext()) {
				log.info("inside loop");
				ValueMap properties = listChildren.next().getProperties();
				vm = new ValueMapDecorator(new HashMap<String, Object>());
				vm.put("value", properties.get("jcr:title", " "));
				vm.put("text", getShortString(properties.get("jcr:title", " ").toString(), 50));
				resourceList.add(new ValueMapResource(request.getResourceResolver(), new ResourceMetadata(),
						"nt:unstructured", vm));
				log.info("resourceList size", resourceList.size());
			}
		}
		DataSource ds = new SimpleDataSource(resourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);

	}

	public String getShortString(String post, int width) {
		if (post != null && post.length() > width) {
			return post.substring(0, width - 3) + "...";
		} else {
			return post;
		}

	}
}
