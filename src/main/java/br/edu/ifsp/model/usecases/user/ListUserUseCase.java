package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.validators.Validator;

import java.util.List;
import java.util.Optional;

public class ListUserUseCase {
    private UserDAO userDAO;

    public ListUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> findOne(Integer id){
        if (id == null){
            throw new IllegalArgumentException("ID can no be null");
        }
        return userDAO.findOne(id);
    }

    /*public Optional<User> findOne(String name){
        if (name == null){
            throw new IllegalArgumentException("Name can no be null");
        }
        return userDAO.findOne(name);
    }*/ //Verificar depois

    public Optional<User> findOneByName(String name){
        if (Validator.nullOrEmpty(name)){
            throw new IllegalArgumentException("Name can no be null or empty");
        }
        return userDAO.findOneByUsername(name);
    }



    public Optional<User> findOneByUsername(String username){
        if (Validator.nullOrEmpty(username)){
            throw new IllegalArgumentException("Username can no be null or empty");
        }
        return userDAO.findOneByUsername(username);
    }

    public Optional<User> findOneByEmail(String email){
        if (Validator.nullOrEmpty(email)){
            throw new IllegalArgumentException("Email can no be null or empty");
        }
        return userDAO.findOneByUsername(email);
    }

    public Optional<List<User>> findAll(){
        return userDAO.findAll();
    }
    //É necessário implementar um metodo que busque pelo status, necessário ver como sera definido o status
}
