package br.edu.ifsp.model.entities.student;

import br.edu.ifsp.model.entities.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

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
