package com.aem.training.servlets;

import java.io.IOException;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/node",
		ServletResolverConstants.SLING_SERVLET_NAME+"=NodeAPI Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=GET"
})
public class NodeAPIDemo extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = request.getParameter("path");
		Session session = request.getResourceResolver().adaptTo(Session.class);
		try {
			Node mainnode = session.getNode(parameter);
			Node addNode = mainnode.addNode("customnode", "nt:unstructured");
			addNode.setProperty("prop1", "prop1");
			session.save();
		} catch (PathNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(null!=parameter) {

				try {
					Node node = session.getNode(parameter);
					if(null!=node) {
						PropertyIterator properties = node.getProperties();
						while(properties.hasNext()) {
							Value value = properties.nextProperty().getValue();
							response.getWriter().write("property value::"+value.toString()+"\n");
						}
						
					}
				} catch (PathNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
		}
	}

}
