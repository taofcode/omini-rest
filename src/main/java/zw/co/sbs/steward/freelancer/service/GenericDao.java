package zw.co.sbs.steward.freelancer.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {
    T save(T entity) throws IOException;

   T getById(Serializable id);
   // T getById(String id);

    List<T> getAll();

    void delete(Serializable id);
}