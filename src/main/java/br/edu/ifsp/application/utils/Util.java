package br.edu.ifsp.application.utils;

import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.InstructorDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Util {

    private static StudentDAO studentDAO = new StudentDAOSQLite();
    private static ListStudentUseCase listStudentUseCase = new ListStudentUseCase(studentDAO);

    private static InstructorDAO instructorDAO = new InstructorDAOSQLite();
    private static ListInstructorUseCase listInstructorUseCase = new ListInstructorUseCase(instructorDAO);


    public static List<String> studentsToName(List<Student> studentList){
        List<String> listNames = new ArrayList<>();
        for (Student student: studentList) {
            listNames.add(student.getName());
        }
        return listNames;
    }

    public static List<String> instructorToName(List<Instructor> instructorList){
        List<String> listNames = new ArrayList<>();
        for (Instructor instructor:  instructorList) {
            listNames.add(instructor.getName());
        }
        return listNames;
    }

    public static Student nameToStudent(String name){
        List<Student> listStudent = listStudentUseCase.findAll().get();
        for (Student student: listStudent) {
            if(student.getName().equals(name)){
                return student;
            }
        }
        return null;
    }

    public static Instructor nameToInstructor(String name){
        List<Instructor> listInstructor = listInstructorUseCase.findAll().get();
        for (Instructor instructor: listInstructor) {
            if(instructor.getName().equals(name)){
                return instructor;
            }
        }
        return null;
    }


    public static LocalDateTime stringToDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDate stringToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
