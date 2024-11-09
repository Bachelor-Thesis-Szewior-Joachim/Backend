package org.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BackendApplication.class, args);

		listAllEndpoints(applicationContext);
	}

	private static void listAllEndpoints(ApplicationContext applicationContext) {
		RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		requestMappingHandlerMapping.getHandlerMethods()
				.forEach((requestMappingInfo, handlerMethod) ->
						System.out.println(requestMappingInfo + " - " + handlerMethod));
	}

}
