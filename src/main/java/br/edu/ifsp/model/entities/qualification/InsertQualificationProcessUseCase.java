package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.TestStatus;
import br.edu.ifsp.model.entities.Validator;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.instructor.InstructorDAO;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.student.StudentDAO;

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
