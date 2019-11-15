 package com.aem.training.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class, property = { ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/queryxpath",
		ServletResolverConstants.SLING_SERVLET_NAME + "=Query XPATH Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS + "=GET" })
public class QueryXPATH extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Stringquery = "SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content]) and CONTAINS(s.*, 'home')";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResourceResolver resolver = request.getResourceResolver();
		QueryBuilder queryBuilder = resolver.adaptTo(QueryBuilder.class);
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("type", "cq:pageContent");
		queryMap.put("path", "/content");
		queryMap.put("property", "navTitle");
		queryMap.put("property.value", "Home");
		
//		queryMap.put("type", "sling:Folder");
//		queryMap.put("path", "/content/dam");
//		queryMap.put("property", "navTitle");
//		queryMap.put("property.value", "Home");

		 Query createQuery = queryBuilder.createQuery(PredicateGroup.create(queryMap), resolver.adaptTo(Session.class));
		 SearchResult result = createQuery.getResult();
		 if(null!=result)
		 {
			 Iterator<Node> nodes = result.getNodes();
			 while(nodes.hasNext())
			 {
				 Node node = nodes.next();
				 try {
					response.getWriter().write(node.getPath()+"\n");
				} catch (RepositoryException e) {
					response.getWriter().write("Exception"+e);

				}
				 
			 }
		 }
	}
}
