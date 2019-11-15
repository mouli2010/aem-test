package com.aem.training.scheduler;


import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Runnable.class,immediate=true)
@Designate(ocd=TestScheduler.Configrations.class)
public class TestScheduler implements Runnable {
 
	@Reference
	ResourceResolverFactory resolverFactory;
	Logger log= LoggerFactory.getLogger(TestScheduler.class);
	private String path;
	@Override
	public void run() {
		log.info("schedular is called {}",path);
		Map<String,Object> param= new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE,"readService");
		try {
			ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);
			Resource resource = resolver.resolve(path);
			log.info(" Resource {}"+resource.getPath());
			if(null!=resource)
			{
				resolver.delete(resource);
				resolver.commit();
				log.info(" Resource deleted {}"+resource.getPath());
			}
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Activate
	protected void activate(Configrations configrations) {
		log.info("inside Activate");
		path = configrations.path();
	}
	@ObjectClassDefinition(name="Test Sehedular Configrations")
	public @interface Configrations {
		@AttributeDefinition(name="CORN Expressions")
	   String scheduler_expression()  default "* * * ? * *";
		@AttributeDefinition(name=" Path")
		   String path()  default "/content/test ";

	}

}
