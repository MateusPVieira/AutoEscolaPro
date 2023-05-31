package br.edu.ifsp.application.view.model.usecases.user;

import br.edu.ifsp.application.view.model.dao.UserDAO;
import br.edu.ifsp.application.view.model.entities.user.User;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.application.view.model.validators.UserInputRequestValidator;
import br.edu.ifsp.application.view.model.validators.Validator;
import br.edu.ifsp.application.view.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.application.view.model.entities.notification.Notification;

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
