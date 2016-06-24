package com.st.nicobot;

import com.st.nicobot.configuration.PersistenceContext;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@EnableAsync
@PropertySource("classpath:nicobot.properties")
@PropertySource(value = "classpath:slack.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:externalapis.properties", ignoreResourceNotFound = true)
@Import(PersistenceContext.class)
public class BotMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new AnnotationConfigApplicationContext(BotMain.class);
	}
}
