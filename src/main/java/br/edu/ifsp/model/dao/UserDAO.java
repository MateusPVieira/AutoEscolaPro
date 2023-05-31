package br.edu.ifsp.application.view.model.dao;

import br.edu.ifsp.application.view.model.entities.user.User;

import java.util.Optional;

public interface UserDAO extends DAO<User, Integer> {

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByName(String name);




}
