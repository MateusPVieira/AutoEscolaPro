package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.EntityNotFoundException;

import java.util.Optional;

public class UserLoginUseCase {
    private final UserDAO userDao;

    public UserLoginUseCase(UserDAO userDao) {
        this.userDao = userDao;
    }

    public UserLoginDTO loginUser(String username, String password) throws InvalidCredentialsException {
        Optional<User> userOptional = userDao.findByUsername(username);
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