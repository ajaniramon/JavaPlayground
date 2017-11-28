package xyz.ajanicorp.playground.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ajanicorp.playground.service.LinkCrudService;
import xyz.ajanicorp.playground.service.impl.LinkCrudServiceImpl;

/**
 * Created by Ajani on 03/10/2017.
 */
@Configuration
public class ApplicationConfiguration {
    @Bean("configurator")
    public ResourceValueProvider getConfigurator(){
        return new ResourceValueProvider();
    }


    @Bean("linkCrudService")
    public LinkCrudService linkCrudService(){
        return new LinkCrudServiceImpl();
    }

    @Bean("gson")
    public Gson gson(){
        return new GsonBuilder().create();
    }
}
