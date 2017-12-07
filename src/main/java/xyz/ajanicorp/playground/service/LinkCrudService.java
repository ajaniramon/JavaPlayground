package xyz.ajanicorp.playground.service;

import xyz.ajanicorp.playground.core.PlaygroundException;
import xyz.ajanicorp.playground.core.PlaygroundResult;
import xyz.ajanicorp.playground.service.impl.GenericCrudServiceImpl;
import xyz.ajanicorp.playground.domain.Link;

import java.util.List;

/**
 * Created by Ajani on 04/11/2017.
 */
public interface LinkCrudService extends GenericCrudService<Link, Integer> {
    public Link findByCode(String code);

    public PlaygroundResult<Link> update(Link link) throws PlaygroundException;

}
