package com.aem.training.workflow;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
@Component(service=WorkflowProcess.class,
property = { Constants.SERVICE_VENDOR + "=Regnant",
		Constants.SERVICE_DESCRIPTION + "=When pagedelete Sendemail to user workflow process", 
		"process.label=Test Email Workflow Process" })
public class Sendmaildeletepage implements WorkflowProcess{
	private static final Logger log = LoggerFactory.getLogger(Sendmaildeletepage.class);
	@Reference
	private MessageGatewayService messageGatewayService;


	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		// TODO Auto-generated method stub
		log.info("Here in execute method"); 
		//Declare a MessageGateway service
		MessageGateway<Email> messageGateway; 
		//Set up the Email message
		Email email = new SimpleEmail();
		//Set the mail values
		String emailToRecipients = "rajamouli2010@gmail.com"; 
		String emailCcRecipients = "onkargupta19@gmail.com";
		try {
		email.addTo(emailToRecipients);
		 email.addCc(emailCcRecipients);
		   email.setSubject("AEM Custom Step");
		   email.setFrom("rajamouli466@gmail.com"); 
		   email.setMsg("Hello....congratulation...u have succesfully delete/move asset the page");
		 //Inject a MessageGateway Service and send the message
		    messageGateway = messageGatewayService.getGateway(Email.class);
		    log.info("Here in method"); 
		 // Check the logs to see that messageGateway is not null
		    messageGateway.send((Email) email);
		}
		catch (EmailException e) {
			log.error("Exception :{}",e);
		}

		
	}

} 










