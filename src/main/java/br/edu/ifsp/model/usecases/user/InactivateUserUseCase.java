package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.enums.RegistrationStatus;

public class InactivateUserUseCase {
    private UserDAO userDAO;
    private UpdateUserUseCase updateUserUseCase;

    public InactivateUserUseCase(UserDAO userDAO, UpdateUserUseCase updateUserUseCase) {
        this.userDAO = userDAO;
        this.updateUserUseCase = updateUserUseCase;
    }

    public boolean inactivateUserUseCase(int id){
        User user = userDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("User not found!"));

        user.setRegistrationStatus(RegistrationStatus.INACTIVE);

        return updateUserUseCase.update(user);
    }
}
