package com.aem.training.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;

@Component(service = { Servlet.class },

property = { ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/json",
ServletResolverConstants.SLING_SERVLET_NAME + "=acceptingjson",
ServletResolverConstants.SLING_SERVLET_METHODS + "=GET" })
public class Json extends SlingAllMethodsServlet {

@Override
protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
throws ServletException, IOException {
String json = request.getParameter("json");
System.out.println(json);
Map<String, String> m = new Gson().fromJson(json, Map.class);
System.out.println(m.get("firstname"));
System.out.println(m.get("lastname"));
response.getWriter().write("hello" + m.get("firstname")+ "gaaru");



}
}