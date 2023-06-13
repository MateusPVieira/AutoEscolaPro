package br.edu.ifsp.application.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DatabaseBuilder {
    private static final Logger logger = LogManager.getLogger(DatabaseBuilder.class);
    //observe as FKs antes de escolher a ordem de criação das tabelas
    //para evitar problemas com tabelas criadas antes das que ela possui FK
    public void buildDatabaseIfMissing(){
        if (isDatabaseMissing()) {
            logger.info("Database is missing. Building database:");
            buildTables(false);
        }
        else {
            logger.info("Database exists. Checking database:");
            buildTables(true);
        }
    }

    private boolean isDatabaseMissing(){
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables(boolean isUpdate){
        try(Statement statement = ConnectionFactory.createStatement()){
            statement.addBatch(createUserTable());

            if (!isUpdate)
                statement.addBatch(createAdminUser());
//              statement.addBatch(createDrivingCategoryTable());
                statement.addBatch(createStudentTable());


//            statement.addBatch(createValuesReferenceTable());
//            statement.addBatch(createScheduleTable());
//            statement.addBatch(createQualificationProcessTable());
            statement.executeBatch();

            logger.info("Database update success!");
        } catch (SQLException e){
            logger.error(e);
        }
    }

    private String createUserTable(){
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

    private String createAdminUser(){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO USER(name, username, password, accessLevel, registrationStatus) VALUES (");
        builder.append("'Administrador', 'admin', 'admin', 'ADMIN', 'ACTIVE');");

        System.out.println(builder.toString());
        return builder.toString();
    }




    private String createDrivingCategoryTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS DrivingCategory (");

        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("vehicle INTEGER");

        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }

    private String createStudentTable(){

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Student (");
        builder.append("name TEXT,");
        builder.append("cpf TEXT,");
        builder.append("rg TEXT,");
        builder.append("cnh TEXT,");
        builder.append("address TEXT,");
        builder.append("phone TEXT,");
        builder.append("email TEXT,");
        builder.append(");");

        logger.info(builder.toString());

        return builder.toString();
    }
    private String createInstructorTable(){

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Instructor (");
        builder.append("name TEXT,");
        builder.append("cpf TEXT,");
        builder.append("rg TEXT,");
        builder.append("cnh TEXT,");
        builder.append("address TEXT,");
        builder.append("phone TEXT,");
        builder.append("bankAccount TEXT,");
        builder.append("registrationStatus TEXT,");
        builder.append(");");

        logger.info(builder.toString());

        return builder.toString();
    }

    private String createInstructorDrivingCategoryTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS InstructorDrivingCategoryTable (");
        builder.append("instructor_id INTEGER,");
        builder.append("driving_category_id INTEGER,");
        builder.append("FOREIGN KEY (instructor_id) REFERENCES instructor(id),;");
        builder.append("FOREIGN KEY (driving_category_id) REFERENCES DrivingCategory(id)");
        builder.append(");");

        logger.info(builder.toString());
        return builder.toString();
    }



    private String createValuesReferenceTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ValuesReference (");

        builder.append("lessonValueInCents INTEGER,");
        builder.append("defaultMinimumNumberOfLessons INTEGER,");
        builder.append("testValueInCents INTEGER,");
        builder.append("drivingSchoolOpeningTime TEXT,");
        builder.append("drivingSchoolClosingTime TEXT,");

        builder.append("drivingCategory TEXT");
        builder.append("FOREIGN KEY(drivingCategory) REFERENCES drivingCategory(id)");

        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }

    private String createScheduleTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Schedule (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("scheduledDateTime TEXT,");
        builder.append("scheduleStatus TEXT,");
        builder.append("remunerationStatus TEXT,");
        builder.append("scheduleType TEXT,");

        builder.append("valuesReference INTEGER");
        builder.append("FOREIGN KEY(valuesReference) REFERENCES valuesReference(id)");
        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }



    private String createQualificationProcessTable(){
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

        builder.append("drivingCategory INTEGER,");
        builder.append("instructorId INTEGER,");
        builder.append("studentId INTEGER");
        builder.append("schedules INTEGER");
        builder.append("FOREIGN KEY(drivingCategory) REFERENCES drivingCategory(id)");
        builder.append("FOREIGN KEY(instructorId) REFERENCES instructor(id)");
        builder.append("FOREIGN KEY(studentId) REFERENCES student(id)");
        builder.append("FOREIGN KEY(schedules) REFERENCES schedule(id)");

        builder.append(");");
        logger.info(builder.toString());
        return builder.toString();
    }

}
