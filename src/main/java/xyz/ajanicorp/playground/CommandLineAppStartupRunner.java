package xyz.ajanicorp.playground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.ajanicorp.playground.configuration.ResourceValueProvider;

/**
 * Created by Ajani on 03/10/2017.
 */

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    ResourceValueProvider resourceValueProvider;

    @Override
    public void run(String...args) throws Exception {
        //for (int i = 0; i < 10; i++) {
            //logger.info(resourceValueProvider.getProfileMessage());
      //  }
            logger.info("Estamos en el aire!");
    }
}