 package com.aem.training.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.training.services.TestService;

@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/osgi",
		ServletResolverConstants.SLING_SERVLET_NAME+"=OSGIServiceTest Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=GET"
})
public class OSGIServiceTest extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Reference 
	TestService testService;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		        String message = testService.getMessage("hello world");
		        response.getWriter().write(message);
		        response.getWriter().write(testService.getMessage());
		
	}

}
