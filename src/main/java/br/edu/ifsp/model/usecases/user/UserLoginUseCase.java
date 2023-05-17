package br.edu.ifsp.model.usecases.user;

import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.*;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InvalidCredentialsException;

import java.util.Optional;
// talvez não retornar usuário e utilizar a sessão pra dados?
public class UserLoginUseCase {
    private final UserDAO userDao;

    public UserLoginUseCase(UserDAO userDao) {
        this.userDao = userDao;
    }

    public UserLoginDTO loginUser(String username, String password) throws InvalidCredentialsException {
        Optional<User> userOptional = userDao.findOneByUsername(username);
        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!password.equals(user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        UserLoginDTO userDto = new UserLoginDTO(user);
        Session session = Session.getInstance();
        session.setUser(userDto);
        return userDto;
    }
}