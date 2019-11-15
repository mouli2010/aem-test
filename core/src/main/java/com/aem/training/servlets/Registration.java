package com.aem.training.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.training.services.RegistrationService;
@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/register",
		ServletResolverConstants.SLING_SERVLET_NAME+"=Register Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=POST,GET"
})
public class Registration extends SlingAllMethodsServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Reference 
	RegistrationService resgister;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		 
		 super.doPost(request, response);
	}
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fname= request.getParameter("firstname");
		 String lname= request.getParameter("lastname");
		 System.out.println(fname+lname);
		 System.out.println("hai mouli");
		 response.getWriter().write("fullname is:"+fname+lname);
		 resgister.insertDataDB(fname, lname);
		
	}
	

}
