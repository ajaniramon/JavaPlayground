package xyz.ajanicorp.playground.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Ajani on 03/10/2017.
 */
@Component
public class Configurator {
    @Value("${profileMessage}")
    String profileMessage;


    @Bean(name="getProfileMessage")
    public String getProfileMessage(){
        return profileMessage;
    }

    public Configurator(){}
}
