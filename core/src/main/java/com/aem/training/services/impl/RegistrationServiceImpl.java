 package com.aem.training.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.training.services.RegistrationService;
import com.day.commons.datasource.poolservice.DataSourcePool;
@Component(service=RegistrationService.class,immediate=true,
property= {
		Constants.SERVICE_VENDOR +"= Regnant software solutions",
		Constants.SERVICE_DESCRIPTION +"=This is sample Description."
})
public class RegistrationServiceImpl implements RegistrationService {
	
	/** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
    @Reference
    private DataSourcePool source;
  
    //Inject the Form Data into a database! 
    @Override
    public void insertDataDB(String fname, String lname)
{
         
        //Simply write out the values that are posted from the AEM form to the AEM log file
        log.info("DB Data posted from an AEM adaptive form - customer_ID: "+fname+" customer_Name: "+lname) ;
         
        Connection c = null;
         
        int rowCount= 0; 
        try {
                          
              // Create a Connection object
              c =  getConnection();
             
//               ResultSet rs = null;
//               Statement s = c.createStatement();
//               Statement scount = c.createStatement();
                   
               //Use prepared statements to protected against SQL injection attacks
//               PreparedStatement pstmt = null;
               PreparedStatement ps = null; 
                 
               String insert = "INSERT INTO \"studentdb\".\"customer\"(fname,lname) VALUES(?, ?);";
               ps = c.prepareStatement(insert);
                 
               ps.setString(1,fname); 
               ps.setString(2, lname);
               
               ps.execute();
                
        }
        catch (Exception e) {
          e.printStackTrace();
         }
        finally {
          try
          {
            c.close();
          }
            
            catch (SQLException e) {
              e.printStackTrace();
            }
    }
        
  }
 
  //Returns a connection using the configured DataSourcePool 
    private Connection getConnection()
    {
             DataSource dataSource = null;
             Connection con = null;
             try
             {
                 //Inject the DataSourcePool right here! 
                 dataSource = (DataSource) source.getDataSource("customer");
                 con = dataSource.getConnection();
                 return con;
                   
               }
             catch (Exception e)
             {
                 e.printStackTrace(); 
             }
                 return null; 
    }
           
  }




