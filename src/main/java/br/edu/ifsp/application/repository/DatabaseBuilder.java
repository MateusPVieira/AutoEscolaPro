package br.edu.ifsp.application.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DatabaseBuilder {
    private static final Logger logger = LogManager.getLogger(DatabaseBuilder.class);
    private String insertValues = "INSERT INTO ValuesReference(lessonValueInCents, defaultMinimumNumberOfLessons, testValueInCents," +
            "drivingSchoolOpeningTime, drivingSchoolClosingTime, drivingCategory) VALUES(";

    //observe as FKs antes de escolher a ordem de criação das tabelas
    //para evitar problemas com tabelas criadas antes das que ela possui FK
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            logger.info("Database is missing. Building database:");
            buildTables(false);
        } else {
            logger.info("Database exists. Checking database:");
            buildTables(true);
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables(boolean isUpdate) {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createUserTable());

            if (!isUpdate) {
                statement.addBatch(createAdminUser());
                statement.addBatch(setValueA());
                statement.addBatch(setValueB());
                statement.addBatch(setValueC());
                statement.addBatch(setValueD());
            }
            statement.addBatch(createStudentTable());
            statement.addBatch(createInstructorTable());
            statement.addBatch(createInstructorDrivingCategoryTable());
            statement.addBatch(createValuesReferenceTable());
            statement.addBatch(createScheduleTable());
            statement.addBatch(createQualificationProcessTable());
            statement.addBatch(createQualificationScheduleTable());
            statement.executeBatch();

            logger.info("Database update success!");
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    private String createUserTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS user (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("name TEXT,");
        builder.append("username TEXT,");
        builder.append("password TEXT,");
        builder.append("email TEXT,");
        builder.append("phone TEXT,");
        builder.append("passwordTips BLOB,");
        builder.append("accessLevel TEXT,");
        builder.append("registrationStatus TEXT");
        builder.append(");");


        logger.info(builder.toString());
        return builder.toString();
    }

    private String createAdminUser() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO USER(name, username, password, accessLevel, registrationStatus) VALUES (");
        builder.append("'Administrador', 'admin', 'admin', 'ADMIN', 'ACTIVE');");

        System.out.println(builder.toString());
        return builder.toString();
    }


    private String createStudentTable() {

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Student (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("name TEXT,");
        builder.append("cpf TEXT,");
        builder.append("rg TEXT,");
        builder.append("cnh TEXT,");
        builder.append("address TEXT,");
        builder.append("phone TEXT,");
        builder.append("email TEXT");
        builder.append(");");

        logger.info(builder.toString());

        return builder.toString();
    }

    private String createInstructorTable() {

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Instructor (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("name TEXT,");
        builder.append("cpf TEXT,");
        builder.append("rg TEXT,");
        builder.append("cnh TEXT,");
        builder.append("address TEXT,");
        builder.append("phone TEXT,");
        builder.append("bankAccount TEXT,");
        builder.append("registrationStatus TEXT");
        builder.append(");");

        logger.info(builder.toString());

        return builder.toString();
    }

    private String createInstructorDrivingCategoryTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS InstructorDrivingCategoryTable (");
        builder.append("instructor_id INTEGER,");
        builder.append("driving_category TEXT");
        builder.append(");");

        logger.info(builder.toString());
        return builder.toString();
    }


    private String createValuesReferenceTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ValuesReference (");

        builder.append("lessonValueInCents INTEGER,");
        builder.append("defaultMinimumNumberOfLessons INTEGER,");
        builder.append("testValueInCents INTEGER,");
        builder.append("drivingSchoolOpeningTime TEXT,");
        builder.append("drivingSchoolClosingTime TEXT,");
        builder.append("drivingCategory TEXT");

        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }

    private String setValueA(){

        StringBuilder builder = new StringBuilder();
        builder.append(insertValues);
        builder.append("4000,");
        builder.append("10,");
        builder.append("10000,");
        builder.append("'06:30:00',");
        builder.append("'20:30:00',");
        builder.append("'A - Carro'");
        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }
    private String setValueB(){
        StringBuilder builder = new StringBuilder();
        builder.append(insertValues);
        builder.append("3000,");
        builder.append("10,");
        builder.append("9000,");
        builder.append("'06:30:00',");
        builder.append("'20:30:00',");
        builder.append("'B - Moto'");
        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }
    private String setValueC(){
        StringBuilder builder = new StringBuilder();
        builder.append(insertValues);
        builder.append("6000,");
        builder.append("20,");
        builder.append("12000,");
        builder.append("'06:30:00',");
        builder.append("'20:30:00',");
        builder.append("'C - Caminhao'");
        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }
    private String setValueD(){
        StringBuilder builder = new StringBuilder();
        builder.append(insertValues);
        builder.append("8000,");
        builder.append("25,");
        builder.append("1500,");
        builder.append("'06:30:00',");
        builder.append("'20:30:00',");
        builder.append("'D - Onibus'");
        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }

    private String createScheduleTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Schedule (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("scheduledDateTime TEXT,");
        builder.append("scheduleStatus TEXT,");
        builder.append("remunerationStatus TEXT,");
        builder.append("scheduleType TEXT,");

        builder.append("valuesReference INTEGER,");
        builder.append("FOREIGN KEY(valuesReference) REFERENCES valuesReference(id)");
        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }


    private String createQualificationProcessTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS QualificationProcess (");

        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("qualificationValueCents INTEGER,");
        builder.append("openingDate TEXT,");
        builder.append("minimumNumberOfLessons INTEGER,");
        builder.append("userId INTEGER,");
        builder.append("registrationStatus TEXT,");
        builder.append("eyeExam TEXT,");
        builder.append("theoricExam TEXT,");
        builder.append("psychoExam TEXT,");

        builder.append("drivingCategory TEXT,");
        builder.append("instructorId INTEGER,");
        builder.append("studentId INTEGER,");
        builder.append("FOREIGN KEY(instructorId) REFERENCES instructor(id),");
        builder.append("FOREIGN KEY(studentId) REFERENCES student(id)");


        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }

    private String createQualificationScheduleTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS QualificationSchedule (");
        builder.append("qualification_id INTEGER,");
        builder.append("schedule_id INTEGER");
        builder.append(");");

        logger.info(builder.toString());
        return builder.toString();
    }

}
