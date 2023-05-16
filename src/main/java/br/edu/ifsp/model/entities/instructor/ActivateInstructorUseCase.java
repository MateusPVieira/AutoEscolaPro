package br.edu.ifsp.model.entities.instructor;
import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class ActivateInstructorUseCase {
    private InstructorDAO instructorDAO;
    private UpdateInstructorUseCase updateInstructorUseCase;
    public ActivateInstructorUseCase(InstructorDAO instructorDAO, UpdateInstructorUseCase updateInstructorUseCase){
        this.instructorDAO = instructorDAO;
        this.updateInstructorUseCase = updateInstructorUseCase;
    }
    public boolean activateInstructor(Long id){
        Instructor instructor = instructorDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        instructor.setRegistrationStatus(RegistrationStatus.ACTIVE);

        return updateInstructorUseCase.update(instructor);
    }



}
