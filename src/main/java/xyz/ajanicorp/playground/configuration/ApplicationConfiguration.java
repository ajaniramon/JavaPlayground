package xyz.ajanicorp.playground.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ajani on 03/10/2017.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean("configurator")
    public Configurator getConfigurator(){
        return new Configurator();
    }

}
