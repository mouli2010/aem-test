package com.aem.training.workflow;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = { Constants.SERVICE_VENDOR + "=Regnant",
		Constants.SERVICE_DESCRIPTION + "=A Sample workflow process", "process.label=SampleWorkflow" })

public class Workflow implements WorkflowProcess {
	Logger log = LoggerFactory.getLogger(Workflow.class);

	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		// TODO Auto-generated method stub
		Session jcrsession = session.adaptTo(Session.class);
		WorkflowData workflowData = item.getWorkflowData();
		String payloadpath = workflowData.getPayload().toString();
		log.info("payload path {}",payloadpath);
		try {
			Node node = jcrsession.getNode(payloadpath);
			if(null!=node ) {
				node = node.hasNode("jcr:content")?node.getNode("jcr:content"):node;
				Property setProperty = node.setProperty("name","value");
				jcrsession.save();
			}
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	}
