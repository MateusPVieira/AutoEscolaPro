package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class ActivateQualificationProcessUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private UpdateQualificationProcessUseCase updateQualificationProcessUseCase;

    public ActivateQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO, UpdateQualificationProcessUseCase updateQualificationProcessUseCase){
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.updateQualificationProcessUseCase = updateQualificationProcessUseCase;
    }

    public boolean activateQualificationProcess(Long id){
       QualificationProcess qualification = qualificationProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

       qualification.setRegistrationStatus(RegistrationStatus.valueOf("ACTIVE"));

       return updateQualificationProcessUseCase.update(qualification.getId(), qualification);
    }
}
