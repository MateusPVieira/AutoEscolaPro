package br.edu.ifsp.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO <T, K>{
    K create (T type);
    Optional<T> findOne(K key);
    List<T> findAll();
    boolean update(T type);
}
