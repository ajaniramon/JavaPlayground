package xyz.ajanicorp.playground.service.impl;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ajanicorp.playground.domain.Link;
import xyz.ajanicorp.playground.repository.LinkRepository;
import xyz.ajanicorp.playground.service.LinkCrudService;

/**
 * Created by Ajani on 04/11/2017.
 */
public class LinkCrudServiceImpl extends GenericCrudServiceImpl<Link, Integer> implements LinkCrudService {

    @Autowired
    private LinkRepository linkRepository;

    private static final Logger logger = LoggerFactory.getLogger(LinkCrudServiceImpl.class);

    @Override
    public Link findByCode(String code) {
        try{
            return linkRepository.findByCode(code);
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            throw e;
        }
    }
}
