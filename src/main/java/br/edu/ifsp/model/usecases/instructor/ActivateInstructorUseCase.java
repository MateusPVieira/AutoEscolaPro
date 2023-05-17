package br.edu.ifsp.model.usecases.instructor;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.enums.RegistrationStatus;

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
