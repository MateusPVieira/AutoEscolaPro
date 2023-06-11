package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOSQLite implements UserDAO {


    @Override
    public Integer create(User user) {
        String sql = "INSERT INTO" +
                "USER (name, username, password, email, phone, accessLevel, registrationStatus)" +
                "VALUES (?, ? , ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAcessLevel().toString());
            statement.setString(7, user.getRegistrationStatus().toString());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return generatedId;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<User> findOne(Integer id) {
        String sql = "SELECT * FROM User WHERE id = '" + id + "';";
        return getUser(sql);
    }


    @Override
    public Optional<List<User>> findAll() {
        var users = new ArrayList<User>();

        String sql = "SELECT * FROM User;";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
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

                users.add(user);
            }
            return Optional.of(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public boolean update(User user) {
        String sql = "UPDATE" +
                "USER SET (name = ?, username = ?, password = ?, email = ?, phone = ?, accessLevel = ?, registrationStatus = ?)" +
                "WHERE id = ?;";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAcessLevel().toString());
            statement.setString(7, user.getRegistrationStatus().toString());
            statement.setInt(8, user.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        String sql = "SELECT * FROM User WHERE email = '" + email + "';";
        return getUser(sql);
    }


    @Override
    public Optional<User> findOneByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = '" + username + "';";
        return getUser(sql);
    }

    @Override
    public Optional<User> findOneByName(String name) {
        String sql = "SELECT * FROM User WHERE name = '" + name + "';";
        return getUser(sql);
    }

    private Optional<User> getUser(String sql) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
