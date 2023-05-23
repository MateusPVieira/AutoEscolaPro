package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcessInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

/**
 * Class responsible for updating qualification processes.
 * This use case provides a method to update a qualification process in the system.
 * It interacts with the QualificationProcessDAO to access and update the qualification process data.
 *
 * @author Mateus Vieira
 */
public class UpdateQualificationProcessUseCase {
    private QualificationProcessDAO qualificationProcessDAO;

    public UpdateQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO){
        this.qualificationProcessDAO = qualificationProcessDAO;
    }

    /**
     * Updates a qualification process with the given ID using the provided qualification process object.
     *
     * @param qualificationId The ID of the qualification process to update.
     * @param qualificationProcess The updated QualificationProcess object.
     * @return true if the update operation is successful, false otherwise.
     * @throws EntityNotFoundException If the qualification process with the given ID is not found in the system.
     * @throws IllegalArgumentException If the provided qualification process fails validation.
     */
    public boolean update(Long qualificationId, QualificationProcess qualificationProcess){
        qualificationProcessDAO.findOne(qualificationId).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        Validator<QualificationProcess> validator = new QualificationProcessInputRequestValidator();

        Notification notification = validator.validate(qualificationProcess);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return qualificationProcessDAO.update(qualificationProcess);
    }
}

