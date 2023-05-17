package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcessInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.enums.TestStatus;
import br.edu.ifsp.model.validators.Validator;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;

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

        QualificationProcess qualificationProcess = new QualificationProcess(
          qualificationValueCents, minimumNumberOfLessons, user, registrationStatus,
          eyeExam, theoricExam, psychoExam, drivingCategory, instructor, student);

        Notification notification = validator.validate(qualificationProcess);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return qualificationProcessDAO.create(qualificationProcess);
    }
}
