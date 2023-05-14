package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

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
