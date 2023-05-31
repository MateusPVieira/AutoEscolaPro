package br.edu.ifsp.application.view.model.usecases.user;

import br.edu.ifsp.application.view.model.dao.UserDAO;
import br.edu.ifsp.application.view.model.entities.user.User;
import br.edu.ifsp.application.view.model.validators.UserInputRequestValidator;
import br.edu.ifsp.application.view.model.validators.Validator;
import br.edu.ifsp.application.view.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.application.view.model.entities.notification.Notification;

public class CreateUserUseCase {
    private UserDAO userDAO;

    public CreateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Integer insert(User user){
        Validator<User> validator = new UserInputRequestValidator();
        Notification notification = validator.validate(user);

        if (notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if(userDAO.findOneByEmail(user.getEmail()).isPresent()){
            throw new EntityAlreadyExistsException("This email is already in use.");
        }

        if(userDAO.findOneByUsername(user.getUsername()).isPresent()){
            throw new EntityAlreadyExistsException("This username is already in use.");
        }

        return userDAO.create(user);
    }
}
