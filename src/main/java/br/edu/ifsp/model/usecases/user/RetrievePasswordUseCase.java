package br.edu.ifsp.application.view.model.usecases.user;

import br.edu.ifsp.application.view.model.dao.UserDAO;
import br.edu.ifsp.application.view.model.entities.user.User;
import br.edu.ifsp.application.view.model.entities.user.UserLoginDTO;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Class responsible for retrieving a password.
 * This use case retrieves password tips associated with the given username.
 * The password tips can be used to help the user remember their password.
 * Note: This use case assumes that the user with the given username exists in the system.
 * If the username is not found, an EntityNotFoundException will be thrown.
 *
 * @author Mateus Vieira
 */
public class RetrievePasswordUseCase {

    private UserDAO userDAO;

    /**
     * Constructor for the RetrievePasswordUseCase class.
     *
     * @param userDAO The UserDAO object used to access user data.
     */
    public RetrievePasswordUseCase(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * Retrieves password tips associated with the given username.
     *
     * @param username The username for which to retrieve the password tips.
     * @return A list of password tips associated with the username.
     * @throws EntityNotFoundException If the username is not found in the system.
     */
    public List<String> retrievePassword(String username){
        User user = userDAO.findOneByUsername(username).orElseThrow(() -> new EntityNotFoundException("Username not found!"));
        UserLoginDTO userLoginDTO = new UserLoginDTO(user);

        return userLoginDTO.getPasswordTips();
    }
}
