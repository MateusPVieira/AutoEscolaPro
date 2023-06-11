package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.Session;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.entities.user.UserLoginDTO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InvalidCredentialsException;

import java.sql.SQLException;
import java.util.Optional;
// talvez não retornar usuário e utilizar a sessão pra dados?
/**
 * Class responsible for user login.
 * This use case performs user login by validating the provided username and password.
 * If the username is not found or the password is incorrect, appropriate exceptions will be thrown.
 * Upon successful login, a UserLoginDTO object is created and returned.
 * The UserLoginDTO contains information about the logged-in user.
 * Additionally, the UserLoginUseCase sets the user session using the Session class.
 * Note: This use case assumes that the user with the given username exists in the system.
 * If the username is not found, an EntityNotFoundException will be thrown.
 *
 * @author Mateus Vieira
 */
public class UserLoginUseCase {

    private final UserDAO userDao;

    /**
     * Constructor for the UserLoginUseCase class.
     *
     * @param userDao The UserDAO object used to access user data.
     */
    public UserLoginUseCase(UserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * Logs in a user with the provided username and password.
     *
     * @param username The username for the user to log in.
     * @param password The password for the user to log in.
     * @return A UserLoginDTO object containing information about the logged-in user.
     * @throws InvalidCredentialsException If the provided credentials are invalid.
     * @throws EntityNotFoundException If the username is not found in the system.
     */
    public boolean loginUser(String username, String password) throws InvalidCredentialsException {
        User user = userDao.findOneByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!password.equals(user.getPassword())) {
            System.out.println(password);
            System.out.println(user.getPassword());
            throw new InvalidCredentialsException();
        }

        UserLoginDTO userDto = new UserLoginDTO(user);
        Session session = Session.getInstance();
        session.setUser(userDto);
        System.out.println("Usuário logado com sucesso!");
        return true;
    }
}
