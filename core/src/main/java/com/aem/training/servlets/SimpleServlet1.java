package com.aem.training.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException; 
 
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.training.services.EmployeeInter;
 
 
@Component(service=Servlet.class,
        property={
                Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths="+ "/bin/myCustData"
           })
public class SimpleServlet1 extends SlingAllMethodsServlet {
  
    private static final long serialVersionUid = 1L;
      
    private final Logger logger = LoggerFactory.getLogger(getClass());
      
    @Reference
    private EmployeeInter emplData;
  
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
         
        try
        {
        logger.info("About to call");  
             
        String data=  emplData.getEmployeeDataQB(); 
        logger.info("DATA IS "+data);   
        resp.getWriter().write(data);
        System.out.println(data);
      
        }
        catch (Exception e)
        {
            e.printStackTrace(); 
        }
                  
        }
   
}
