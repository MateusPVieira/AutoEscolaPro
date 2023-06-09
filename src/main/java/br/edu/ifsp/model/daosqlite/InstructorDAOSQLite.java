package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.enums.RegistrationStatus;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import br.edu.ifsp.model.entities.category.DrivingCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Optional;


public class InstructorDAOSQLite implements InstructorDAO {
    private static final Logger logger = LogManager.getLogger(InstructorDAOSQLite.class);

    @Override
    public Long create(Instructor instructor) {
        String sql = "INSERT INTO" +
                " INSTRUCTOR (name, cpf, rg, cnh, address, phone, bankAccount, registrationStatus)" +
                " VALUES (?, ? , ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, instructor.getName());
            statement.setString(2, instructor.getCpf());
            statement.setString(3, instructor.getRg());
            statement.setString(4, instructor.getCnh());
            statement.setString(5, instructor.getAddress());
            statement.setString(6, instructor.getPhone());
            statement.setString(7, instructor.getBankAccount());
            statement.setString(8, instructor.getRegistrationStatus().toString());

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
    public Optional<Instructor> findOne(Long id) {
        String sql = "SELECT * FROM INSTRUCTOR WHERE id = '" + id + "';";
        return getInstructor(sql);
    }

    @Override
    public Optional<List<Instructor>> findAll() {
        var instructors = new ArrayList<Instructor>();

        String sql = "SELECT * FROM INSTRUCTOR;";
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
                String dbbankacc = rs.getString("bankAccount");
                String dbstatus = rs.getString("registrationStatus");

                Instructor instructor = new Instructor(dbid, dbname, dbcpf, dbrg, dbcnh, dbadress, dbphone,dbbankacc, RegistrationStatus.valueOf(dbstatus));
                instructor.setDrivingCategory(getListCategory(instructor.getId()));
                instructors.add(instructor);
            }

            return Optional.of(instructors);
        } catch (Exception e) {
            logger.error(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Instructor type) {
        return false;
    }

    @Override
    public Optional<Instructor> findOneByCPF(String cpf) {
        String sql = "SELECT * FROM INSTRUCTOR WHERE cpf = '" + cpf + "';";
        return getInstructor(sql);
    }

    @Override
    public Optional<Instructor> findOneByRG(String rg) {
        String sql = "SELECT * FROM INSTRUCTOR WHERE rg = '" + rg + "';";
        return getInstructor(sql);
    }

    @Override
    public Optional<Instructor> findOneByCNH(String cnh) {
        String sql = "SELECT * FROM INSTRUCTOR WHERE cnh = '" + cnh + "';";
        return getInstructor(sql);
    }

    @Override
    public boolean insertDrivingCategory(Instructor instructor) {
        String sql = "Insert INTO InstructorDrivingCategoryTable (instructor_id, driving_category) values (?, ?);";

        for (DrivingCategory drivingCategory : instructor.getDrivingCategory()) {
            try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
                statement.setLong(1, instructor.getId());
                statement.setString(2, drivingCategory.toString());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }

    public List<DrivingCategory> getListCategory(Long id){
        var listCategory = new ArrayList<DrivingCategory>();
        String sql = "SELECT * FROM InstructorDrivingCategoryTable WHERE instructor_id = " + id;
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String dbCategory = rs.getString("driving_category");
                String category = String.valueOf(dbCategory.charAt(0));
                listCategory.add(DrivingCategory.valueOf(category));
            }
            return listCategory;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private Optional<Instructor> getInstructor(String sql) {
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            long dbid = rs.getLong("id");
            String dbname = rs.getString("name");
            String dbcpf = rs.getString("cpf");
            String dbrg = rs.getString("rg");
            String dbcnh = rs.getString("cnh");
            String dbadress = rs.getString("address");
            String dbphone = rs.getString("phone");
            String dbbankacc = rs.getString("bankAccount");
            String dbstatus = rs.getString("registrationStatus");
            Instructor instructor = new Instructor(dbid, dbname, dbcpf, dbrg, dbcnh, dbadress, dbphone,dbbankacc, RegistrationStatus.valueOf(dbstatus));
            instructor.setDrivingCategory(getListCategory(instructor.getId()));

            return Optional.of(instructor);
        } catch (Exception e) {
            logger.error(e);
        }
        return Optional.empty();
    }


}