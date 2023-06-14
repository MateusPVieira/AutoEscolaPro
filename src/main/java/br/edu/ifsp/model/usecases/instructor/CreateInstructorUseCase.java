package br.edu.ifsp.model.usecases.instructor;

import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.validators.InstructorInputRequestValidator;
import br.edu.ifsp.model.validators.Validator;
import br.edu.ifsp.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.notification.Notification;

/**
 * Class responsible for handling the use case of creating a new instructor.
 * It interacts with the InstructorDAO to perform database operations.
 * @author Stefhani Alkin
 */
public class CreateInstructorUseCase {
    private InstructorDAO instructorDAO;

    /**
     * Constructs a CreateInstructorUseCase object with the specified InstructorDAO.
     *
     * @param instructorDAO the data access object for instructors
     */
    public CreateInstructorUseCase(InstructorDAO instructorDAO){
        this.instructorDAO = instructorDAO;
    }

    /**
     * Inserts a new instructor into the system.
     *
     * @param instructor the instructor object to be inserted
     * @return the ID of the newly inserted instructor
     * @throws IllegalArgumentException if the instructor object is invalid
     * @throws EntityAlreadyExistsException if the instructor's CPF, RG, or CNH already exist in the system
     */
    public Long insert(Instructor instructor){
     //   Validator<Instructor> validator = new InstructorInputRequestValidator();
      //  Notification notification = validator.validate(instructor);
      //  if(notification.hasErrors())
        //    throw new IllegalArgumentException(notification.errorMessage());

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

    public boolean insertDrivingCategory(Instructor instructor){
        return instructorDAO.insertDrivingCategory(instructor);
    }
}
