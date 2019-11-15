package com.aem.training.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.factory.ModelFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.training.models.TestAdapters;
@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/model",
		ServletResolverConstants.SLING_SERVLET_NAME+"=model Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=GET"
})
public class ModelServlet extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Reference
	ModelFactory mf;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		//Resource resource=request.getResourceResolver().getResource("/content/homepage/jcr:content");
		Resource resource=request.getResourceResolver().getResource("/content/homepage/jcr:content");
//		TestAdapters  test = resource.adaptTo(TestAdapters.class);
//		response.getWriter().print(mf.canCreateFromAdaptable(resource, TestAdapters.class)+"\n");
//		response.getWriter().print(test.getType());
//		
		TestAdapters test = mf.createModel(resource, TestAdapters.class);
		response.getWriter().print(mf.canCreateFromAdaptable(resource, TestAdapters.class)+"\n");
		response.getWriter().print(mf.canCreateFromAdaptable(request, TestAdapters.class)+"\n");
		
		
	}
	

}
