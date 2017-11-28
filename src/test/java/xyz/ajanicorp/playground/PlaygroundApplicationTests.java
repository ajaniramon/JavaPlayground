package xyz.ajanicorp.playground;

import com.google.common.base.Throwables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ajanicorp.playground.configuration.ApplicationConfiguration;
import xyz.ajanicorp.playground.configuration.H2TestProfileJPAConfig;
import xyz.ajanicorp.playground.domain.Link;
import xyz.ajanicorp.playground.repository.LinkRepository;
import xyz.ajanicorp.playground.service.LinkCrudService;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		PlaygroundApplication.class,
		H2TestProfileJPAConfig.class, ApplicationConfiguration.class
})
@ActiveProfiles("test")
public class PlaygroundApplicationTests {

	@Autowired
	LinkCrudService linkCrudService;

	private static final Logger logger = LoggerFactory.getLogger(PlaygroundApplicationTests.class);

	@Test
	public void databaseIsWorkingTest() {
		try{
			List<Link> links = linkCrudService.findAll();

			for(Link link: links) linkCrudService.delete(link.getId());

			Link link = new Link();

			link.setCode("TEST");
			link.setDestinationUrl("http://localhost:8080/");
			link.setName("Pagina personal");

			linkCrudService.save(link);

			assertTrue(linkCrudService.findAll().size() == 1);
		}catch(Exception e){
			logger.error("Algo ocurre con la BD, amigo. \n"+ Throwables.getStackTraceAsString(e));
			fail();
		}
	}
}
