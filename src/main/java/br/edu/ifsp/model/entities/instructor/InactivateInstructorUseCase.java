package br.edu.ifsp.model.entities.instructor;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class InactivateInstructorUseCase {
    private InstructorDAO instructorProcessDAO;
    private UpdateInstructorUseCase updateInstructorUseCase;

    public InactivateInstructorUseCase(InstructorDAO instructorProcessDAO, UpdateInstructorUseCase updateInstructorUseCase){
        this.instructorProcessDAO = instructorProcessDAO;
        this.updateInstructorUseCase = updateInstructorUseCase;
    }

    public boolean inactivateInstructor(Long id){
        Instructor instructor = instructorProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        instructor.setRegistrationStatus(RegistrationStatus.INACTIVE);

        return updateInstructorUseCase.update(instructor.getId(), instructor);
    }
}
