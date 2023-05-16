package br.edu.ifsp.model.entities.instructor;

import br.edu.ifsp.model.entities.EntityAlreadyExistsException;
import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

public class UpdateInstructorUseCase {
    private InstructorDAO instructorDAO;

    public UpdateInstructorUseCase(InstructorDAO instructorDAO){
        this.instructorDAO = instructorDAO;
    }

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