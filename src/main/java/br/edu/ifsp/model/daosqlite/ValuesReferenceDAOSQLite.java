package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.model.dao.ReferenceValuesDAO;
import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.entities.reference.ValuesReference;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValuesReferenceDAOSQLite implements ReferenceValuesDAO {
    @Override
    public Long create(ValuesReference valuesReference) {
        String sql = "INSERT INTO" +
                " ValuesReference (DRIVING_SCHOOL_OPENING_TIME, DRIVING_SCHOOL_CLOSING_TIME, lessonValueInCents, defaultMinimunNumberOfLessons, TestValueInCents, drivingCategory)" +
                " VALUES (?, ? , ?, ?, ?, ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, ValuesReference.getDrivingSchoolOpeningTime().toString());
            statement.setString(2, ValuesReference.getDrivingSchoolClosingTime().toString());
            statement.setLong(3, valuesReference.getLessonValueInCents());
            statement.setInt(4, valuesReference.getDefaultMinimunNumberOfLessons());
            statement.setLong(5, valuesReference.getTestValueInCents());
            statement.setString(6, valuesReference.getDrivingCategory().toString());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating valuesReference failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    return generatedId;
                } else {
                    throw new SQLException("Creating valuesReference failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ValuesReference> findOne(Long id) {
        String sql = "SELECT * FROM ValuesReference WHERE id = " + id + ";";

        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            var dbId = rs.getLong("id");
            var dbDOP = rs.getString("DRIVING_SCHOOL_OPENING_TIME");
            var dbDCT = rs.getString("DRIVING_SCHOOL_CLOSING_TIME");
            var dbLessonValueInCents = rs.getLong("lessonValueInCents");
            var dbDMNL = rs.getInt("defaultMinimunNumberOfLessons");
            var dbTestValueInCents = rs.getLong("TestValueInCents");
            var dbDrivingCategory = rs.getString("drivingCategory");
            String category = String.valueOf(dbDrivingCategory.charAt(0));

            ValuesReference valuesReference = new ValuesReference(dbId, dbLessonValueInCents, dbDMNL, dbTestValueInCents, DrivingCategory.valueOf(category));
            return Optional.of(valuesReference);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<ValuesReference>> findAll() {
        List<ValuesReference> listValuesReference = new ArrayList<>();
        String sql = "SELECT * FROM ValuesReference;";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                var dbId = rs.getLong("id");
                var dbDOP = rs.getString("DRIVING_SCHOOL_OPENING_TIME");
                var dbDCT = rs.getString("DRIVING_SCHOOL_CLOSING_TIME");
                var dbLessonValueInCents = rs.getLong("lessonValueInCents");
                var dbDMNL = rs.getInt("defaultMinimunNumberOfLessons");
                var dbTestValueInCents = rs.getLong("TestValueInCents");
                var dbDrivingCategory = rs.getString("drivingCategory");
                String category = String.valueOf(dbDrivingCategory.charAt(0));

                ValuesReference valuesReference = new ValuesReference(dbId, dbLessonValueInCents, dbDMNL, dbTestValueInCents, DrivingCategory.valueOf(category));
                listValuesReference.add(valuesReference);
            }
            return Optional.of(listValuesReference);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean update(ValuesReference type) {
        return false;
    }

    @Override
    public Optional<ValuesReference> findOneByKeycategory(DrivingCategory category) {
        String sql = "SELECT * FROM ValuesReference WHERE drivingCategory = " + category + ";";

        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            var dbId = rs.getLong("id");
            var dbDOP = rs.getString("DRIVING_SCHOOL_OPENING_TIME");
            var dbDCT = rs.getString("DRIVING_SCHOOL_CLOSING_TIME");
            var dbLessonValueInCents = rs.getLong("lessonValueInCents");
            var dbDMNL = rs.getInt("defaultMinimunNumberOfLessons");
            var dbTestValueInCents = rs.getLong("TestValueInCents");
            var dbDrivingCategory = rs.getString("drivingCategory");
            String dbcategory = String.valueOf(dbDrivingCategory.charAt(0));


            ValuesReference valuesReference = new ValuesReference(dbId, dbLessonValueInCents, dbDMNL, dbTestValueInCents, category);
            return Optional.of(valuesReference);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
