package br.edu.ifsp.model.usecases.instructor;

import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.validators.InstructorInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

public class CreateInstructorUseCase {
    private InstructorDAO instructorDAO;

    public CreateInstructorUseCase(InstructorDAO instructorDAO){
        this.instructorDAO = instructorDAO;
    }

    public Long insert(Instructor instructor){
        Validator<Instructor> validator = new InstructorInputRequestValidator();
        Notification notification = validator.validate(instructor);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = instructor.getCpf();
        if(instructorDAO.findOneByCPF(cpf).isPresent())
            throw new EntityAlreadyExistsException("This CPF is already in use!");

        String rg = instructor.getRg();
        if(instructorDAO.findOneByRG(rg).isPresent())
            throw new EntityAlreadyExistsException("This RG is already in use!");

        String cnh = instructor.getCnh();
        if(instructorDAO.findOneByCNH(cnh).isPresent())
            throw new EntityAlreadyExistsException("This CNH is already in use!");

        /*if(instructorDAO.findOneByEmail(instructor.getEmail()).isPresent())
            throw new EntityAlreadyExistsException("This e-mail is already in use!");*/

        return instructorDAO.create(instructor);

    }
    // Igual a do Student, só que pra o Instrutor
}