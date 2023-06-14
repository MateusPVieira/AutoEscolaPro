package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAOSQLite implements StudentDAO {
    private static final Logger logger = LogManager.getLogger(StudentDAOSQLite.class);
    @Override
    public Long create(Student student) {
        String sql = "INSERT INTO" +
                " STUDENT (name, cpf, rg, cnh, address, phone, email)" +
                " VALUES (?, ? , ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getCpf());
            statement.setString(3, student.getRg());
            statement.setString(4, student.getCnh());
            statement.setString(5, student.getAddress());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getEmail());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    return generatedId;
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    @Override
    public Optional<Student> findOne(Long id) {
        String sql = "SELECT * FROM Student WHERE id = '" + id + "';";
        return getStudent(sql);
    }

    @Override
    public Optional<List<Student>> findAll() {
        var students = new ArrayList<Student>();

        String sql = "SELECT * FROM Student;";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                long dbid = rs.getLong("id");
                String dbname = rs.getString("name");
                String dbcpf = rs.getString("cpf");
                String dbrg = rs.getString("rg");
                String dbcnh = rs.getString("cnh");
                String dbadress = rs.getString("address");
                String dbphone = rs.getString("phone");
                String dbemail = rs.getString("email");
                Student student = new Student(dbid, dbname, dbcpf, dbrg, dbcnh, dbadress, dbphone,dbemail);
                students.add(student);
            }
            return Optional.of(students);
        } catch (Exception e) {
            logger.error(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE " +
                "STUDENT SET name = ?, cpf = ?, rg = ?, cnh = ?, address = ?, phone = ?, email = ?" +
                " WHERE id = ?;";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getCpf());
            statement.setString(3, student.getRg());
            statement.setString(4, student.getCnh());
            statement.setString(5, student.getAddress());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getEmail());
            statement.setLong(8, student.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating student failed, no rows affected.");
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public Optional<Student> findOneByCPF(String cpf) {
        String sql = "SELECT * FROM Student WHERE cpf = '" + cpf + "';";
        return getStudent(sql);
    }

    @Override
    public Optional<Student> findOneByEmail(String email) {
        String sql = "SELECT * FROM Student WHERE email = '" + email + "';";
        return getStudent(sql);
    }

    @Override
    public Optional<Student> findOneByRG(String rg) {
        String sql = "SELECT * FROM Student WHERE rg = '" + rg + "';";
        return getStudent(sql);
    }

    @Override
    public Optional<Student> findOneByCNH(String cnh) {
        String sql = "SELECT * FROM Student WHERE cnh = '" + cnh + "';";
        return getStudent(sql);
    }

    private Optional<Student> getStudent(String sql) {
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            long dbid = rs.getLong("id");
            String dbname = rs.getString("name");
            String dbcpf = rs.getString("cpf");
            String dbrg = rs.getString("rg");
            String dbcnh = rs.getString("cnh");
            String dbadress = rs.getString("address");
            String dbphone = rs.getString("phone");
            String dbemail = rs.getString("email");
            Student student = new Student(dbid, dbname, dbcpf, dbrg, dbcnh, dbadress, dbphone,dbemail);
            return Optional.of(student);
        } catch (Exception e) {
            logger.error(e);
        }
        return Optional.empty();
    }
}
