package br.edu.ifsp.application.view.model.usecases.instructor;
import br.edu.ifsp.application.view.model.dao.InstructorDAO;
import br.edu.ifsp.application.view.model.entities.instructor.Instructor;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.application.view.model.enums.RegistrationStatus;

/**
 * Class responsible for activating an instructor.
 *
 * @author Mateus Vieira
 */
public class ActivateInstructorUseCase {

    private InstructorDAO instructorDAO;
    private UpdateInstructorUseCase updateInstructorUseCase;

    /**
     * Constructor for the ActivateInstructorUseCase class.
     *
     * @param instructorDAO             The InstructorDAO object used to access instructor data.
     * @param updateInstructorUseCase   The UpdateInstructorUseCase use case used to update the instructor.
     */
    public ActivateInstructorUseCase(InstructorDAO instructorDAO, UpdateInstructorUseCase updateInstructorUseCase){
        this.instructorDAO = instructorDAO;
        this.updateInstructorUseCase = updateInstructorUseCase;
    }

    /**
     * Activates an instructor based on the provided ID.
     *
     * @param id    The ID of the instructor to activate.
     * @return      True if the instructor was successfully activated, False otherwise.
     * @throws EntityNotFoundException    If the qualification process is not found.
     */
    public boolean activateInstructor(Long id){
        Instructor instructor = instructorDAO.findOne(id).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        instructor.setRegistrationStatus(RegistrationStatus.ACTIVE);

        return updateInstructorUseCase.update(instructor);
    }
}


