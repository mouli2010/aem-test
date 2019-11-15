package com.aem.training.servlets;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.aem.training.models.Jsonmodel;
import com.google.gson.Gson;

@Component(service = { Servlet.class }, property = { ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/url",
		ServletResolverConstants.SLING_SERVLET_NAME + "=urlservlet",
		ServletResolverConstants.SLING_SERVLET_METHODS + "=GET" })
public class UrlFromServlet extends SlingAllMethodsServlet {
	

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		//Gson gson=new Gson();
		String charset="UTF-8";


		URLConnection url = new URL("https://jsonplaceholder.typicode.com/todos/1").openConnection();
	   url.setRequestProperty("Accept-Charset", charset);
		InputStream inputStream = url.getInputStream();
		String string = IOUtils.toString(inputStream);
		System.out.println(string);
		response.getWriter().write(string);
		Map<String, String> fromJson = new Gson().fromJson(string, Map.class);
		System.out.println(fromJson);
		//Jsonmodel jsonmodel = request.adaptTo(com.aem.training.models.Jsonmodel.class);

		/*for (Map.Entry<String, String> entryset : fromJson.entrySet()) {
			response.getWriter().write(entryset.getKey() + " <----------> " + entryset.getValue());
	}
*/
//		
//		fromJson.forEach((key,value) -> {
//			try {
//				response.getWriter().write(key + " = " + value);
//				System.out.println(key + " = " + value);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		
		
	}
 
}
