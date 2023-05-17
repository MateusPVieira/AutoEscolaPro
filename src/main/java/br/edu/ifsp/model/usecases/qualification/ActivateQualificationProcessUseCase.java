package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.enums.RegistrationStatus;

public class ActivateQualificationProcessUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private UpdateQualificationProcessUseCase updateQualificationProcessUseCase;

    public ActivateQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO, UpdateQualificationProcessUseCase updateQualificationProcessUseCase){
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.updateQualificationProcessUseCase = updateQualificationProcessUseCase;
    }

    public boolean activateQualificationProcess(Long id){
       QualificationProcess qualification = qualificationProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

       qualification.setRegistrationStatus(RegistrationStatus.ACTIVE);

       return updateQualificationProcessUseCase.update(qualification.getId(), qualification);
    }
}
