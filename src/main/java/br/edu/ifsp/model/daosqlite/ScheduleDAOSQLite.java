package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.application.utils.Util;
import br.edu.ifsp.model.dao.ReferenceValuesDAO;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.enums.RemunerationStatus;
import br.edu.ifsp.model.enums.ScheduleStatus;
import br.edu.ifsp.model.enums.ScheduleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDAOSQLite implements ScheduleDAO {
    private final ReferenceValuesDAO referenceValuesDAO = new ValuesReferenceDAOSQLite();

    @Override
    public Long create(Schedule schedule) {
        String sql = "INSERT INTO" +
                " Schedule (scheduledDateTime, scheduleStatus, remunerationStatus, valuesReference, scheduleType)" +
                " VALUES (?, ? , ?, ?, ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setString(1, schedule.getScheduledDateTime().toString());
            statement.setString(2, schedule.getScheduleStatus().toString());
            statement.setString(3, schedule.getRemunerationStatus().toString());
            statement.setLong(4, schedule.getValuesReference().getId());
            statement.setString(5, schedule.getScheduleType().toString());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating schedule failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    return generatedId;
                } else {
                    throw new SQLException("Creating schedule failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Schedule> findOne(Long id) {
        String sql = "SELECT * FROM SCHEDULE WHERE id = " + id + ";";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            var dbId = rs.getLong("id");
            var dbSchDateTime = rs.getString("scheduledDateTime");
            var dbSchStatus = rs.getString("scheduleStatus");
            var dbRemunerationStatus = rs.getString("remunerationStatus");
            var dbSchType = rs.getString("scheduleType");
            var dbValuesReference = rs.getLong("valuesReference");

            var valueRef = referenceValuesDAO.findOne(dbValuesReference).get();

            Schedule schedule = new Schedule(dbId, Util.stringToDateTime(dbSchDateTime), ScheduleStatus.valueOf(dbSchStatus),
                    RemunerationStatus.valueOf(dbRemunerationStatus), valueRef, ScheduleType.valueOf(dbSchType));
            return Optional.of(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

        @Override
        public Optional<List<Schedule>> findAll () {
            List<Schedule> listSchedule = new ArrayList<>();
            String sql = "SELECT * FROM SCHEDULE;";
            try (Statement statement = ConnectionFactory.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {

                    var dbId = rs.getLong("id");
                    var dbSchDateTime = rs.getString("scheduledDateTime");
                    var dbSchStatus = rs.getString("scheduleStatus");
                    var dbRemunerationStatus = rs.getString("remunerationStatus");
                    var dbSchType = rs.getString("scheduleType");
                    var dbValuesReference = rs.getLong("valuesReference");

                    var valueRef = referenceValuesDAO.findOne(dbValuesReference).get();

                    Schedule schedule = new Schedule(dbId, Util.stringToDateTime(dbSchDateTime), ScheduleStatus.valueOf(dbSchStatus),
                            RemunerationStatus.valueOf(dbRemunerationStatus), valueRef, ScheduleType.valueOf(dbSchType));
                    listSchedule.add(schedule);
                }
                return Optional.of(listSchedule);
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public boolean update (Schedule type){
            return false;
        }

        @Override
        public Optional<List<Schedule>> findSomeByInstructor (Instructor instructor){
            return Optional.empty();
        }

        @Override
        public Optional<List<Schedule>> findSomeByQualificationProcess (QualificationProcess qualificationProcess){
            return Optional.empty();
        }

    }
