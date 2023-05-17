package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.validators.UserInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

public class UpdateUserUseCase {
    private UserDAO userDAO;

    public UpdateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean update(User user){
        Validator<User> validator = new UserInputRequestValidator();
        Notification notification = validator.validate(user);

        if (notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        int id = user.getId();
        if(userDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("User not found.");
        }
        if(userDAO.findOneByEmail(user.getEmail()).isPresent()){
            throw new EntityAlreadyExistsException("This email is already in use.");
        }

        return userDAO.update(user);
    }
}
