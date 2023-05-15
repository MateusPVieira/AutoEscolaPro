package br.edu.ifsp.model.entities.instructor;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class ActivateInstructorUseCase {
    private InstructorDAO instructorProcessDAO;
    private UpdateInstructorUseCase updateInstructorUseCase;

    public ActivateInstructorUseCase(InstructorDAO instructorProcessDAO, UpdateInstructorUseCase updateInstructorUseCase){
        this.instructorProcessDAO = instructorProcessDAO;
        this.updateInstructorUseCase = updateInstructorUseCase;
    }

    public boolean activateInstructor(Long id){
        Instructor instructor = instructorProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        instructor.setRegistrationStatus(RegistrationStatus.ACTIVE);

        return updateInstructorUseCase.update(instructor.getId(), instructor);
    }
}
