package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.enums.RegistrationStatus;

public class ActivateUserUseCase {
    private UserDAO userDAO;
    private UpdateUserUseCase updateUserUseCase;

    public ActivateUserUseCase(UserDAO userDAO, UpdateUserUseCase updateUserUseCase) {
        this.userDAO = userDAO;
        this.updateUserUseCase = updateUserUseCase;
    }

    public boolean activateUser(int id){
        User user = userDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        user.setRegistrationStatus(RegistrationStatus.ACTIVE);

        return updateUserUseCase.update(user);
    }
}
