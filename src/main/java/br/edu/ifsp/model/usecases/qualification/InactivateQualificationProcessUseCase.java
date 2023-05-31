package br.edu.ifsp.application.view.model.usecases.qualification;

import br.edu.ifsp.application.view.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.application.view.model.dao.QualificationProcessDAO;
import br.edu.ifsp.application.view.model.enums.RegistrationStatus;

/**
 * Class responsible for inactivating qualification processes.
 * This use case provides a method to inactivate a qualification process in the system.
 * It interacts with the QualificationProcessDAO and UpdateQualificationProcessUseCase to access and update the qualification process data.
 * Note: This use case assumes that the qualification process with the given ID exists in the system.
 * If the qualification process is not found, an EntityNotFoundException will be thrown.
 *
 * @author Mateus Vieira
 */
public class InactivateQualificationProcessUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private UpdateQualificationProcessUseCase updateQualificationProcessUseCase;

    /**
     * Constructor for the InactivateQualificationProcessUseCase class.
     *
     * @param qualificationProcessDAO The QualificationProcessDAO object used to access qualification process data.
     * @param updateQualificationProcessUseCase The UpdateQualificationProcessUseCase object used to update qualification processes.
     */
    public InactivateQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO, UpdateQualificationProcessUseCase updateQualificationProcessUseCase){
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.updateQualificationProcessUseCase = updateQualificationProcessUseCase;
    }

    /**
     * Inactivates a qualification process with the given ID.
     *
     * @param id The ID of the qualification process to inactivate.
     * @return true if the inactivation is successful, false otherwise.
     * @throws EntityNotFoundException If the qualification process with the given ID is not found in the system.
     */
    public boolean inactivateQualificationProcess(Long id){
        QualificationProcess qualification = qualificationProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

        qualification.setRegistrationStatus(RegistrationStatus.INACTIVE);

        return updateQualificationProcessUseCase.update(qualification.getId(), qualification);
    }
}

