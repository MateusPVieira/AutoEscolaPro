package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDAOSQLite implements UserDAO {


    @Override
    public Integer create(User type) {
        return null;
    }

    @Override
    public Optional<User> findOne(Integer key) {
    return null;
    }



    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(User type) {
        return false;
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = '" + username + "';";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

                int dbid = rs.getInt("id");
                String dbname = rs.getString("name");
                String dbusername = rs.getString("username");
                String dbpassword = rs.getString("password");
                String dbemail = rs.getString("email");
                String dbphone = rs.getString("phone");
                //String passwordTips
                String dbaccessLevel = rs.getString("accessLevel");
                String dbregistrationStatus = rs.getString("registrationStatus");
                User user = new User(dbid, dbname, dbusername, dbpassword, dbemail, dbphone, AcessLevel.valueOf(dbaccessLevel), RegistrationStatus.valueOf(dbregistrationStatus));
                return Optional.of(user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findOneByName(String name) {
        return Optional.empty();
    }
}
