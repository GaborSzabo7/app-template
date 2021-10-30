package hu.gaszabo.template.infrastructure.logging;

import java.util.List;

import org.apache.catalina.Valve;
import org.apache.catalina.valves.HealthCheckValve;
import org.apache.catalina.valves.RemoteIpValve;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.access.tomcat.LogbackValve;

@Configuration
public class LogbackAccessLogConfiguration {

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addContextValves(valves());
		return tomcat;
	}

	private Valve[] valves() {
		final LogbackValve logbackValve = new LogbackValve();
		logbackValve.setFilename("logback-access.xml");
		logbackValve.setAsyncSupported(true);

		return List.of( //
				logbackValve, //
				new RemoteIpValve(), //
				new StuckThreadDetectionValve(), //
				new HealthCheckValve()) //
				.stream() //
				.toArray(Valve[]::new);
	}

}
