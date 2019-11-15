package com.aem.training.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/nodepost",
		ServletResolverConstants.SLING_SERVLET_NAME+"=NodeAPI PostServlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=POST"
})
public class NodeAPIPostDemo extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String parameter = request.getParameter("path");
		 String name = request.getParameter("name");
		 String value = request.getParameter("value");
		 response.getWriter().write("parameter :: "+parameter+"name :: "+name+"value :: "+value);
			if(null!=parameter && null!=name && null!=value) {
				Session session = request.getResourceResolver().adaptTo(Session.class);

					try {
						Node node = session.getNode(parameter); 
						if(null!=node ) {
						node.setProperty(name,value);
						session.save();
							response.getWriter().write("property set sucessfully");
							
 						}
					} catch (PathNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.getWriter().write("property setting failed"+e.getMessage());
					} catch (RepositoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.getWriter().write("property setting failed"+e.getMessage());
					}
		
				
			}
	}

}
