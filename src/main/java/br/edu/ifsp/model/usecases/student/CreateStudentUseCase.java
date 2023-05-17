package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.validators.StudentInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

public class CreateStudentUseCase {
    private StudentDAO studentDAO;

    public CreateStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    public Long insert(Student student){
        Validator<Student> validator = new StudentInputRequestValidator();
        Notification notification = validator.validate(student);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = student.getCpf();
        if(studentDAO.findOneByCPF(cpf).isPresent())
            throw new EntityAlreadyExistsException("This CPF is already in use!");

        String rg = student.getRg();
        if(studentDAO.findOneByRG(rg).isPresent())
            throw new EntityAlreadyExistsException("This RG is already in use!");

        String cnh = student.getCnh();
        if(studentDAO.findOneByCNH(cnh).isPresent())
            throw new EntityAlreadyExistsException("This CNH is already in use!");

        if(studentDAO.findOneByEmail(student.getEmail()).isPresent())
            throw new EntityAlreadyExistsException("This e-mail is already in use!");

        return studentDAO.create(student);

    }
}
