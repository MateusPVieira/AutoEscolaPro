package br.edu.ifsp.model.usecases.instructor;

import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.enums.RegistrationStatus;

/**
 * Class responsible for inactivating an instructor.
 *
 * @author Mateus Vieira
 */
public class InactivateInstructorUseCase {

    private InstructorDAO instructorDAO;
    private UpdateInstructorUseCase updateInstructorUseCase;

    /**
     * Constructor for the InactivateInstructorUseCase class.
     *
     * @param instructorDAO             The InstructorDAO object used to access instructor data.
     * @param updateInstructorUseCase   The UpdateInstructorUseCase use case used to update the instructor.
     */
    public InactivateInstructorUseCase(InstructorDAO instructorDAO, UpdateInstructorUseCase updateInstructorUseCase){
        this.instructorDAO = instructorDAO;
        this.updateInstructorUseCase = updateInstructorUseCase;
    }

    /**
     * Inactivates an instructor based on the provided ID.
     *
     * @param id    The ID of the instructor to inactivate.
     * @return      True if the instructor was successfully inactivated, False otherwise.
     * @throws EntityNotFoundException    If the qualification process is not found.
     */
    public boolean inactivateInstructor(Long id){
        Instructor instructor = instructorDAO.findOne(id).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        instructor.setRegistrationStatus(RegistrationStatus.INACTIVE);

        return updateInstructorUseCase.update(instructor);
    }
}

