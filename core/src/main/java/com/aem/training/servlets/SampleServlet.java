package com.aem.training.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/test",
		ServletResolverConstants.SLING_SERVLET_NAME+"=Sample Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=GET"
})
public class SampleServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = request.getParameter("path");
		if(null!=parameter) {
			Resource resource = request.getResourceResolver().resolve(parameter);
			if(null!= resource && resource.hasChildren() )
			{
				Iterator<Resource>listChildren=resource.listChildren();
				while(listChildren.hasNext())
				{
					Resource childResource=listChildren.next();
					String name = childResource.getName();
					response.getWriter().write("Sample Servlet "+name+"\n");
				
				}
			}
		}
	}

}
