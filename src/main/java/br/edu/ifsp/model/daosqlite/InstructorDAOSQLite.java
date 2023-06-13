package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorDAOSQLite implements InstructorDAO {
    private static final Logger logger = LogManager.getLogger(InstructorDAOSQLite.class);

    @Override
    public Long create(Instructor instructor) {
        String sql = "INSERT INTO" +
                "INSTRUCTOR (name, cpf, rg, cnh, address, phone, bankAccount, registrationStatus)" +
                "VALUES (?, ? , ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, instructor.getName());
            statement.setString(2, instructor.getCpf());
            statement.setString(3, instructor.getRg());
            statement.setString(4, instructor.getAddress());
            statement.setString(5, instructor.getPhone());
            statement.setString(6, instructor.getBankAccount());
            statement.setString(7, instructor.getRegistrationStatus().toString());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating instructor failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    return generatedId;
                } else {
                    throw new SQLException("Creating instructor failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Optional<Instructor> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Instructor>> findAll() {
        return Optional.empty();
    }

    @Override
    public boolean update(Instructor type) {
        return false;
    }

    @Override
    public Optional<Instructor> findOneByCPF(String cpf) {
        return Optional.empty();
    }

    @Override
    public Optional<Instructor> findOneByRG(String rg) {
        return Optional.empty();
    }

    @Override
    public Optional<Instructor> findOneByCNH(String cnh) {
        return Optional.empty();
    }
}
