package com.aem.training.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Test Service")
public @interface TestConfigurations {
	@AttributeDefinition(name="Message",description="this is one sample test message")
	String getMessage();

}
