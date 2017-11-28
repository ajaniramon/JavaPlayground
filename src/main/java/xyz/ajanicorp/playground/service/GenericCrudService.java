package xyz.ajanicorp.playground.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ajani on 04/11/2017.
 */
public interface GenericCrudService<T, ID extends Serializable> {

     List<T> findAll();

     T find(ID id);

     T save(T t);

     void delete(ID id);
}
