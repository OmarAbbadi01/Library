package org.omm.inftastructure.dao;

import java.util.List;

public interface GenericDao<E> {

    E findById(Long id) throws Exception;

    List<E> findAll() throws Exception;

    void create(E entity) throws Exception;

    void delete(Long id) throws Exception;

    void update(E entity) throws Exception;

    boolean existsById(Long id) throws Exception;

}
