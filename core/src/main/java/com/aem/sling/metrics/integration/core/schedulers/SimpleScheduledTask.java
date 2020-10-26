package com.aem.sling.metrics.integration.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple demo for cron-job like tasks that get executed regularly. It also
 * demonstrates how property values can be set. Users can set the property
 * values in /system/console/configMgr
 */
@Designate(ocd = SimpleScheduledTask.Config.class)
@Component(service = Runnable.class)
public class SimpleScheduledTask implements Runnable {

	@ObjectClassDefinition(name = "Simple Scheduler", description = "Simple demo for cron-job like task with properties")
	public static @interface Config {
		@AttributeDefinition(name = "Scheduler Name",
                description = "Name for the scheduler")
        String schedulerName() default "Simple Scheduler";
		
		@AttributeDefinition(name = "Enabled", description = "Enable Scheduler")
		boolean serviceEnabled() default true;

		@AttributeDefinition(name = "Cron-job expression")
		String scheduler_expression() default "*/30 * * * * ?";

		@AttributeDefinition(name = "Concurrent task", description = "Whether or not to schedule this task concurrently")
		boolean scheduler_concurrent() default false;

		@AttributeDefinition(name = "A parameter", description = "Can be configured in /system/console/configMgr")
		String myParameter() default "";
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String myParameter;

	@Override
	public void run() {
		logger.debug("SimpleScheduledTask is now running, myParameter='{}'", myParameter);
	}

	@Activate
	@Modified
	protected void activate(final Config config) {
		myParameter = config.myParameter();
		run();
	}

}
