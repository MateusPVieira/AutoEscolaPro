package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcessInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

public class UpdateQualificationProcessUseCase {
    private QualificationProcessDAO qualificationProcessDAO;

    public UpdateQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO){
        this.qualificationProcessDAO = qualificationProcessDAO;
    }

    public boolean update(Long qualificationId, QualificationProcess qualificationProcess){
        qualificationProcessDAO.findOne(qualificationId).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        Validator<QualificationProcess> validator = new QualificationProcessInputRequestValidator();

        Notification notification = validator.validate(qualificationProcess);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        return qualificationProcessDAO.update(qualificationProcess);
    }
}
