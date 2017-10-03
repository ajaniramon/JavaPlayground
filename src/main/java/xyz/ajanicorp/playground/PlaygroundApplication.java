package xyz.ajanicorp.playground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.ajanicorp.playground.configuration.Configurator;

@SpringBootApplication
public class PlaygroundApplication {

	static Logger logger = LoggerFactory.getLogger(PlaygroundApplication.class);


	public static void main(String[] args) {


		SpringApplication.run(PlaygroundApplication.class, args);
	}
}
