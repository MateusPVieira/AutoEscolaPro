package br.edu.ifsp.model.usecases.instructor;

import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.validators.InstructorInputRequestValidator;
import br.edu.ifsp.model.validators.Validator;
import br.edu.ifsp.model.entities.notification.Notification;

/**
 * Class responsible for handling the use case of updating an existing instructor.
 * It interacts with the InstructorDAO to perform database operations.
 * @author Stefhani Alkin
 */
public class UpdateInstructorUseCase {
    private InstructorDAO instructorDAO;

    /**
     * Constructs an UpdateInstructorUseCase object with the specified InstructorDAO.
     *
     * @param instructorDAO the data access object for instructors
     */
    public UpdateInstructorUseCase(InstructorDAO instructorDAO){
        this.instructorDAO = instructorDAO;
    }

    /**
     * Updates an existing instructor in the system.
     *
     * @param instructor the instructor object to be updated
     * @return true if the update was successful, false otherwise
     * @throws IllegalArgumentException if the instructor object is invalid
     * @throws EntityNotFoundException if the instructor with the specified ID is not found in the system
     */
    public boolean update(Instructor instructor){
        Validator<Instructor> validator = new InstructorInputRequestValidator();
        Notification notification = validator.validate(instructor);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Long id = instructor.getId();
        if(instructorDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Instructor not found!");

        return instructorDAO.update(instructor);
    }
}