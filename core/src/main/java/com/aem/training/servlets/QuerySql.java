package com.aem.training.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service=Servlet.class,property= {
		ServletResolverConstants.SLING_SERVLET_PATHS+"=/bin/query",
		ServletResolverConstants.SLING_SERVLET_NAME+"=Query Sql2 Servlet",
		ServletResolverConstants.SLING_SERVLET_METHODS+"=GET"
})
public class QuerySql extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Stringquery="SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content]) and CONTAINS(s.*, 'home')";
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String parameter = request.getParameter("path");
//			 Session session = request.getResourceResolver().adaptTo(Session.class);
//			 try {
//				QueryManager queryManager = session.getWorkspace().getQueryManager();
//				Query query = queryManager.createQuery(Stringquery,Query.JCR_SQL2);
//				QueryResult queryResult = query.execute();
//				if(null!=queryResult)
//				{
//				NodeIterator nodes = queryResult.getNodes();
//				while(nodes.hasNext())
//				{
//					Node node = nodes.nextNode();
//					response.getWriter().write(node.getPath()+"\n");
//					
//				}
//				}
//			} catch (RepositoryException e) {
//				response.getWriter().write("Exception"+e);
//			}
			  String query="SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content]) and CONTAINS(s.*, 'home')";
	   Iterator<Resource> resources = request.getResourceResolver().findResources(query, Query.JCR_SQL2);
		
	   while (resources.hasNext()) {
		Resource resource = (Resource) resources.next();
		
		response.getWriter().write(resource.getPath());
	}
		
		
		}
	}

 