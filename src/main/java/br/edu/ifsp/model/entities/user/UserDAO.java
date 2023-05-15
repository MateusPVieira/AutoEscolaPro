package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.DAO;

import java.util.Optional;

public interface UserDAO extends DAO<User, Integer> {

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByName(String name);




}