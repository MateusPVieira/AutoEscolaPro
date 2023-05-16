package br.edu.ifsp.model.entities.instructor;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class InactivateInstructorUseCase {
    private InstructorDAO instructorDAO;
    private UpdateInstructorUseCase updateInstructorUseCase;

    public InactivateInstructorUseCase(InstructorDAO instructorDAO, UpdateInstructorUseCase updateInstructorUseCase){
        this.instructorDAO = instructorDAO;
        this.updateInstructorUseCase = updateInstructorUseCase;
    }

    public boolean inactivateInstructor(Long id){
        Instructor instructor = instructorDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        instructor.setRegistrationStatus(RegistrationStatus.INACTIVE);

        return updateInstructorUseCase.update(instructor);
    }
}
