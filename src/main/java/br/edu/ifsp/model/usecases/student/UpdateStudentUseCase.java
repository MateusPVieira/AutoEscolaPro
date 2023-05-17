package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.validators.StudentInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

public class UpdateStudentUseCase {
    private StudentDAO studentDAO;

    public UpdateStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    public boolean update(Student student){
        Validator<Student> validator = new StudentInputRequestValidator();
        Notification notification = validator.validate(student);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Long id = student.getId();
        if(studentDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Student not found!");

        return studentDAO.update(student);

    }

}
