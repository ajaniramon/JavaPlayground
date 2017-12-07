package xyz.ajanicorp.playground.service.impl;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ajanicorp.playground.constants.PlaygroundStatusCodes;
import xyz.ajanicorp.playground.core.PlaygroundException;
import xyz.ajanicorp.playground.core.PlaygroundMessage;
import xyz.ajanicorp.playground.core.PlaygroundResult;
import xyz.ajanicorp.playground.domain.Link;
import xyz.ajanicorp.playground.repository.LinkRepository;
import xyz.ajanicorp.playground.service.LinkCrudService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PlaygroundResult<Link> update(Link link) throws PlaygroundException {

        try{
            if(link.getId() == null || link.getId() == 0)
                throw new PlaygroundException(new PlaygroundMessage("id","is required"));

            if(this.find(link.getId()) == null)
                return new PlaygroundResult<Link>(PlaygroundStatusCodes.NOT_EXISTS);

            Link updatedLink = linkRepository.save(link);

            return new PlaygroundResult<Link>(PlaygroundStatusCodes.SUP,updatedLink);
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            throw e;
        }
    }
}
