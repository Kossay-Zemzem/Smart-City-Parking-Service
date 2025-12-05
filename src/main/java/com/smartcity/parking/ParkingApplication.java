package com.smartcity.parking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ParkingApplication {
	private static final Logger LG =LogManager.getLogger();

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(ParkingApplication.class, args);
		LG.info("Smart City Parking Application is running...");

		Environment env = ctx.getEnvironment();
		// try runtime property (useful when server.port=0)
		String port = env.getProperty("local.server.port");
		if (port == null || port.isEmpty() || "0".equals(port)) {
			// fallback to configured server.port or default
			port = env.getProperty("server.port", "8080");
		}
		if (port == null || port.isEmpty()) {
			port = "PORT";
		}

        LG.info("Access GraphiQL at: http://localhost:{}/graphiql", port);
	}

}
