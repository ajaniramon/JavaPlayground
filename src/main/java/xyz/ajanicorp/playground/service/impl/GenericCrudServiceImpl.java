package xyz.ajanicorp.playground.service.impl;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ajanicorp.playground.service.GenericCrudService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ajani on 04/11/2017.
 */
public class GenericCrudServiceImpl<T, ID extends Serializable> implements GenericCrudService<T,ID> {

    @Autowired
    JpaRepository<T, ID> jpaRepository;

    private static final Logger logger = LoggerFactory.getLogger(GenericCrudServiceImpl.class);

    @Override
    public List<T> findAll() {
        try{
            return jpaRepository.findAll();
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            throw e;
        }
    }

    @Override
    public T find(ID id) {
        try{
            return jpaRepository.findOne(id);
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            throw e;
        }
    }

    @Override
    public T save(T t) {
        try{
            return jpaRepository.saveAndFlush(t);
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            throw e;
        }
    }


    @Override
    public void delete(ID id) {
        try{
            jpaRepository.delete(id);
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            throw e;
        }
    }
}
