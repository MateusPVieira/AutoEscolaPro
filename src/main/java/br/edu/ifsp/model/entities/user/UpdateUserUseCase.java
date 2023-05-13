package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

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
