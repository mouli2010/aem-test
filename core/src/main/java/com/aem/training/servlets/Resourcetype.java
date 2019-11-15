package com.aem.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES+"=my-aem-project/components/content/imagewithlink" ,
		ServletResolverConstants.SLING_SERVLET_NAME+"=Sample Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=GET",
		ServletResolverConstants.SLING_SERVLET_SELECTORS+"=group",
		
})
public class Resourcetype extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private static final Logger log = LoggerFactory.getLogger(Resourcetype.class);
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try
	      {
	          log.info("---> THIS IS THE GET METHOD OF slingSevletApp/components/page/slingTemplate");
	          PrintWriter out = response.getWriter();
	          out.println("<html><body>");
	          out.println("<h1>This value was returned by an AEM Sling Servlet bound by using a Sling ResourceTypes property</h1>");
	          out.println("</html></body>");
	           
	      }
	      catch(Exception e)
	      {
	          log.info(e.getMessage(),e);
	      }
	}

}
