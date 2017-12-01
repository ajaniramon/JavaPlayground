package xyz.ajanicorp.playground;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.ajanicorp.playground.configuration.ResourceValueProvider;
import xyz.ajanicorp.playground.domain.Link;
import xyz.ajanicorp.playground.repository.LinkRepository;

import java.util.List;

/**
 * Created by Ajani on 03/10/2017.
 */

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    ResourceValueProvider resourceValueProvider;

    @Autowired
    LinkRepository linkRepository;

    @Override
    public void run(String...args) throws Exception {
            logger.info("Estamos en el aire!");
            loadData();
    }

    private void loadData(){
        try{
            List<Link> totalLinks = linkRepository.findAll();

            int totalLinksCount = totalLinks.size();

            if(totalLinksCount < 1){
                Link link1 = new Link();
                link1.setCode("GOOG");
                link1.setDestinationUrl("http://www.google.es");
                link1.setName("Google");

                Link link2 = new Link();
                link2.setCode("YT");
                link2.setDestinationUrl("http://www.youtube.com");
                link2.setName("YouTube");

                Link link3 = new Link();
                link3.setCode("ADT");
                link3.setDestinationUrl("http://www.addingtechnology.es");
                link3.setName("Adding");

                linkRepository.save(link1);
                linkRepository.save(link2);
                linkRepository.save(link3);

                logger.info("[Playground] Carga de datos mínimos completada");
            }
        }catch(Exception e){
            logger.error("[Playground] Error en la carga de datos mínimos");
            logger.error(Throwables.getStackTraceAsString(e));
        }
    }
}
