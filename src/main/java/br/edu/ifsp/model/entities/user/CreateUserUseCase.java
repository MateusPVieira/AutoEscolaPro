package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

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
