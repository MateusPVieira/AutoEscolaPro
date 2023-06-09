package br.edu.ifsp.application.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    //observe as FKs antes de escolher a ordem de criação das tabelas
    //para evitar problemas com tabelas criadas antes das que ela possui FK
    public void buildDatabaseIfMissing(){
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing(){
        return !Files.exists(Paths.get("database.db"));
    }
    private void buildTables(){
        try(Statement statement = ConnectionFactory.createStatement()){
            statement.addBatch(createUserTable());
            statement.addBatch(createDrivingCategoryTable());
            statement.addBatch(createValuesReferenceTable());
            statement.addBatch(createScheduleTable());
            statement.addBatch(createQualificationProcessTable());

            System.out.println("Database creation success!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private String createUserTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE user (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("name TEXT,");
        builder.append("username TEXT,");
        builder.append("email TEXT,");
        builder.append("phone TEXT,");
        builder.append("passwordTips BLOB,");
        builder.append("accessLevel TEXT,");
        builder.append("registrationStatus TEXT");
        builder.append(")");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createDrivingCategoryTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE DrivingCategory (");

        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("vehicle INTEGER");

        builder.append(")");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createValuesReferenceTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ValuesReference (");

        builder.append("lessonValueInCents INTEGER,");
        builder.append("defaultMinimumNumberOfLessons INTEGER,");
        builder.append("testValueInCents INTEGER,");
        builder.append("drivingSchoolOpeningTime TEXT,");
        builder.append("drivingSchoolClosingTime TEXT,");

        builder.append("drivingCategory TEXT");
        builder.append("FOREIGN KEY(drivingCategory) REFERENCES drivingCategory(id)");

        builder.append(")");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createScheduleTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Schedule (");
        builder.append("id INTEGER PRIMARY KEY,");
        builder.append("scheduledDateTime TEXT,");
        builder.append("scheduleStatus TEXT,");
        builder.append("remunerationStatus TEXT,");
        builder.append("scheduleType TEXT,");

        builder.append("valuesReference INTEGER");
        builder.append("FOREIGN KEY(valuesReference) REFERENCES valuesReference(id)");
        builder.append(")");
        System.out.println(builder.toString());
        return builder.toString();
    }



    private String createQualificationProcessTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE QualificationProcess (");

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

        builder.append(")");
        System.out.println(builder.toString());
        return builder.toString();
    }
}
