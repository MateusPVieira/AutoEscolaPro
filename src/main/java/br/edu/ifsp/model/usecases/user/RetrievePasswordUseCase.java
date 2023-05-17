package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.UserLoginDTO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;

import java.util.List;

public class RetrievePasswordUseCase {
    private UserDAO userDAO;

    public RetrievePasswordUseCase(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<String> retrievePassword(String username){
        User user = userDAO.findOneByUsername(username).orElseThrow(()-> new EntityNotFoundException("Username not found!"));
        UserLoginDTO userLoginDTO = new UserLoginDTO(user);

        return userLoginDTO.getPasswordTips();
    }
}
