package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class InactivateUserUseCase {
    private UserDAO userDAO;
    private UpdateUserUseCase updateUserUseCase;

    public InactivateUserUseCase(UserDAO userDAO, UpdateUserUseCase updateUserUseCase) {
        this.userDAO = userDAO;
        this.updateUserUseCase = updateUserUseCase;
    }

    public boolean inactivateUserUseCase(int id){
        User user = userDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        user.setRegistrationStatus(RegistrationStatus.INACTIVE);

        return updateUserUseCase.update(user);
    }
}
