package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.application.repository.ConnectionFactory;
import br.edu.ifsp.application.utils.Util;
import br.edu.ifsp.model.dao.*;
import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QualificationProcessDAOSQLite implements QualificationProcessDAO {

    private final ReferenceValuesDAO referenceValuesDAO= new ValuesReferenceDAOSQLite();
    private final StudentDAO studentDAO = new StudentDAOSQLite();
    private final InstructorDAO instructorDAO = new InstructorDAOSQLite();

    private final UserDAO userDAO = new UserDAOSQLite();

    private final ScheduleDAO scheduleDAO = new ScheduleDAOSQLite();

    @Override
    public Long create(QualificationProcess qualification) {
        String sql = "INSERT INTO" +
                " QualificationProcess (qualificationValueCents, openingDate, minimumNumberOfLessons, userId, registrationStatus, " +
                "eyeExam, theoricExam, psychoExam, drivingCategory, instructorId, studentId )" +
                " VALUES (?, ? , ?, ?, ?, ?, ?, ?, ? , ? , ?);";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setLong(1, qualification.getQualificationValueCents());
            statement.setString(2, qualification.getOpeningDate().toString());
            statement.setInt(3, qualification.getMinimumNumberOfLessons());
            statement.setInt(4, qualification.getUser().getId());
            statement.setString(5, qualification.getRegistrationStatus().toString());
            statement.setString(6, qualification.getEyeExam().toString());
            statement.setString(7, qualification.getTheoricExam().toString());
            statement.setString(8, qualification.getPsychoExam().toString());
            statement.setString(9, qualification.getDrivingCategory().toString());
            statement.setLong(10, qualification.getInstructor().getId());
            statement.setLong(11, qualification.getStudent().getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating qualification failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    insertSchedules(qualification.getDrivingLessons(), generatedId);
                    insertSchedules(qualification.getDrivingTests(), generatedId);
                    return generatedId;
                } else {
                    throw new SQLException("Creating qualification failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<QualificationProcess> findOne(Long id) {
        String sql = "SELECT * FROM QualificationProcess WHERE id = " + id + ";";
        return getQualification(sql);
    }
    @Override
    public Optional<QualificationProcess> findByStudentId(Long id){
        String sql = "SELECT * FROM QualificationProcess WHERE studentId = " + id + ";";
        return getQualification(sql);
    }

    @Override
    public Optional<List<QualificationProcess>> findAll() {
        var qualificationProcesses = new ArrayList<QualificationProcess>();

        String sql = "SELECT * FROM QualificationProcess;";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                long dbId = rs.getLong("id");
                long dbQlValueCents = rs.getLong("qualificationValueCents");
                LocalDate dbOpeningDate = Util.stringToDate(rs.getString("openingDate"));
                int dbMinNumberLessons = rs.getInt("minimumNumberOfLessons");
                int dbUserId = rs.getInt("userId");
                RegistrationStatus dbStatus= RegistrationStatus.valueOf(rs.getString("registrationStatus"));
                TestStatus dbEyeExam = TestStatus.valueOf(rs.getString("eyeExam"));
                TestStatus dbTheoricExam = TestStatus.valueOf(rs.getString("theoricExam"));
                TestStatus dbPsychoExam = TestStatus.valueOf(rs.getString("psychoExam"));
                String dbDrivingCategory = rs.getString("drivingCategory");
                Long dbInstructorId = rs.getLong("instructorId");
                Long dbStudentId = rs.getLong("studentId");
                String category = String.valueOf(dbDrivingCategory.charAt(0));


                Student student = studentDAO.findOne(dbStudentId).get();
                Instructor instructor = instructorDAO.findOne(dbInstructorId).get();
                User user = userDAO.findOne(dbUserId).get();

                QualificationProcess qualificationProcess = new QualificationProcess(dbId, dbQlValueCents, dbOpeningDate, dbMinNumberLessons, user
                        , dbStatus, dbEyeExam, dbTheoricExam, dbPsychoExam, DrivingCategory.valueOf(category), instructor, student);

                for (Schedule schedule: getListSchedule(dbId)) {
                    if(schedule.getScheduleType().equals(ScheduleType.LESSON)){
                        qualificationProcess.addDrivingLesson(schedule);
                    } else {
                        qualificationProcess.addDrivingTest(schedule);
                    }
                }

                qualificationProcesses.add(qualificationProcess);
            }

            return Optional.of(qualificationProcesses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(QualificationProcess qualification) {
        String sql = "UPDATE" +
                " QualificationProcess SET qualificationValueCents = ?, openingDate = ?, minimumNumberOfLessons = ?, userId = ?, registrationStatus = ?, " +
                "eyeExam = ?, theoricExam = ?, psychoExam = ?, drivingCategory = ?, instructorId = ?, studentId = ? )" +
                " WHERE id  = ?;";

        try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            statement.setLong(1, qualification.getQualificationValueCents());
            statement.setString(2, qualification.getOpeningDate().toString());
            statement.setInt(3, qualification.getMinimumNumberOfLessons());
            statement.setInt(4, qualification.getUser().getId());
            statement.setString(5, qualification.getRegistrationStatus().toString());
            statement.setString(6, qualification.getEyeExam().toString());
            statement.setString(7, qualification.getTheoricExam().toString());
            statement.setString(8, qualification.getPsychoExam().toString());
            statement.setString(9, qualification.getDrivingCategory().toString());
            statement.setLong(10, qualification.getInstructor().getId());
            statement.setLong(11, qualification.getStudent().getId());
            statement.setLong(12, qualification.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update qualification failed, no rows affected.");
            }
            insertSchedules(qualification.getDrivingLessons(), qualification.getId());
            insertSchedules(qualification.getDrivingTests(), qualification.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private Optional<QualificationProcess> getQualification(String sql) {
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            long dbId = rs.getLong("id");
            long dbQlValueCents = rs.getLong("qualificationValueCents");
            LocalDate dbOpeningDate = Util.stringToDate(rs.getString("openingDate"));
            int dbMinNumberLessons = rs.getInt("minimumNumberOfLessons");
            int dbUserId = rs.getInt("userId");
            RegistrationStatus dbStatus= RegistrationStatus.valueOf(rs.getString("registrationStatus"));
            TestStatus dbEyeExam = TestStatus.valueOf(rs.getString("eyeExam"));
            TestStatus dbTheoricExam = TestStatus.valueOf(rs.getString("theoricExam"));
            TestStatus dbPsychoExam = TestStatus.valueOf(rs.getString("psychoExam"));
            String dbDrivingCategory = rs.getString("drivingCategory");
            Long dbInstructorId = rs.getLong("instructorId");
            Long dbStudentId = rs.getLong("studentId");

            String dbCategory = rs.getString("driving_category");
            String category = String.valueOf(dbCategory.charAt(0));


            Student student = studentDAO.findOne(dbStudentId).get();
            Instructor instructor = instructorDAO.findOne(dbInstructorId).get();
            User user = userDAO.findOne(dbUserId).get();

            QualificationProcess qualificationProcess = new QualificationProcess(dbId, dbQlValueCents, dbOpeningDate, dbMinNumberLessons, user
                    , dbStatus, dbEyeExam, dbTheoricExam, dbPsychoExam, DrivingCategory.valueOf(category), instructor, student);

            for (Schedule schedule: getListSchedule(dbId)) {
                if(schedule.getScheduleType().equals(ScheduleType.LESSON)){
                    qualificationProcess.addDrivingLesson(schedule);
                } else {
                    qualificationProcess.addDrivingTest(schedule);
                }
            }
            excluirIDsNaoPermitidos(qualificationProcess.getDrivingLessons(), qualificationProcess.getDrivingTests());

            return Optional.of(qualificationProcess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    public boolean insertSchedules(List<Schedule> schedules, Long qualificationId) {
        String sql = "Insert INTO QualificationSchedule (qualification_id, schedule_id) values (?, ?);";

        for (Schedule schedule : schedules) {
            if(scheduleDAO.findOne(schedule.getId()).isEmpty()) {
                try (PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
                    statement.setLong(1, qualificationId);
                    statement.setLong(2, schedule.getId());
                    statement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public List<Schedule> getListSchedule(Long id){
        var listSchedule = new ArrayList<Schedule>();

        String sql = "SELECT Schedule.*" +
                " FROM Schedule" +
                " JOIN QualificationSchedule ON Schedule.id = QualificationSchedule.schedule_id" +
                " WHERE QualificationSchedule.qualification_id = " + id +";";
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                var dbId = rs.getLong("id");
                var dbSchDateTime = rs.getString("scheduledDateTime");
                var dbSchStatus = rs.getString("scheduleStatus");
                var dbRemunerationStatus= rs.getString("remunerationStatus");
                var dbSchType = rs.getString("scheduleType");
                var dbValuesReference = rs.getLong("valuesReference");

                var valueRef = referenceValuesDAO.findOne(dbValuesReference).get();

                Schedule schedule = new Schedule(dbId, Util.stringToDateTime(dbSchDateTime), ScheduleStatus.valueOf(dbSchStatus),
                        RemunerationStatus.valueOf(dbRemunerationStatus), valueRef, ScheduleType.valueOf(dbSchType));
                listSchedule.add(schedule);
            }
            return listSchedule;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void excluirIDsNaoPermitidos(List<Schedule> lessons, List<Schedule> tests ) {
        Optional<List<Schedule>> schedules = scheduleDAO.findAll();
        List<Long> idsParaExcluir = new ArrayList<>();
        List<Schedule> lessonsAndTests =new ArrayList<>();
        lessonsAndTests.addAll(lessons);
        lessonsAndTests.addAll(tests);
        if (schedules.isPresent()) {
            List<Schedule> schedulesOptPresent = schedules.get();
            // Percorre o arrayPrincipal
            for (Schedule schedule : schedulesOptPresent) {
                long id = schedule.getId();
                boolean encontrado = false;

                // Verifica se o ID está contido no arrayPermitido
                for (Schedule lOrT : lessonsAndTests) {
                    if (lOrT.getId() == id) {
                        encontrado = true;
                        break;
                    }
                }

                // Se o ID não estiver contido no arrayPermitido, aciona o método de exclusão
                if (!encontrado) {
                    deletarID(id);
                    idsParaExcluir.add(id);
                }
            }

            System.out.println("IDs excluídos: " + idsParaExcluir);
        }


    }

    public void deletarID(long id) {
        scheduleDAO.deleteScheduleQualification(id);
    }
}


