package fr.home.mikedev.cryptotools;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptoToolsApplication {

    @Value("${tomcat.ajp.port}") int ajpPort;
    
	public static void main(String[] args) {
		SpringApplication.run(CryptoToolsApplication.class, args);
	}
	
    @Bean
    public ServletWebServerFactory servletContainer() 
    {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        Connector ajpConnector = new Connector("AJP/1.3");
        ajpConnector.setPort(ajpPort);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        ((AbstractAjpProtocol<?>) ajpConnector.getProtocolHandler()).setSecret("Thisasecretbetweenapacheandtomcat");
        tomcat.addAdditionalTomcatConnectors(ajpConnector);
 
        return tomcat;
    }
}
