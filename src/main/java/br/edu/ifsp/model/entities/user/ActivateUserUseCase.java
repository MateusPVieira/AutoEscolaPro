package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

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
