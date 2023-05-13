package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.TestStatus;
import br.edu.ifsp.model.entities.Validator;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.student.StudentDAO;
import br.edu.ifsp.model.entities.student.StudentInputRequestValidator;

import java.util.Optional;

public class InsertQualificationProcessUseCase {
    private StudentDAO studentDAO;
    private InstructorDAO instructorDAO;
    private QualificationProcessDAO qualificationProcessDAO;

    public InsertQualificationProcessUseCase(StudentDAO studentDAO, InstructorDAO instructorDAO, QualificationProcessDAO qualificationProcessDAO){
        this.studentDAO = studentDAO;
        this.instructorDAO = instructorDAO;
        this.qualificationProcessDAO = qualificationProcessDAO;
    }

    public Long Insert(long studentId, long instructorId, TestStatus eyeExam, TestStatus psychoExam, TestStatus theoricExam, int numberOfLessons){
        Validator<QualificationProcess> validator = new QualificationProcessInputRequestValidator();


        Student student = studentDAO.findOne(studentId).orElseThrow(()-> new EntityNotFoundException("Student not found!"));
        Instructor instructor = instructorDAO.findOne(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor not found!"));



        QualificationProcess qualificationProcess =new QualificationProcess(
//                id, qualificationValueCents, open/
//                this.id = id;
//        this.qualificationValueCents = qualificationValueCents;
//        this.openingDate = openingDate;
//        this.minimumNumberOfLessons = minimumNumberOfLessons;
//        this.user = user;
//        this.registrationStatus = registrationStatus;
//        this.eyeExam = eyeExam;
//        this.theoricExam = theoricExam;
//        this.psychoExam = psychoExam;
//        this.drivingCategory = drivingCategory;
//        this.instructor = instructor;
//        this.student = student;
//        this.drivingLessons = drivingLessons;
//        this.drivingTests = drivingTests;
        );
        Notification notification = validator.validate(qualificationProcess);

        return qualificationProcessDAO.create(qualificationProcess);
    }
}
