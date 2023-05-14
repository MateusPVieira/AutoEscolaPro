package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;

public class InactivateQualificationProcessUseCase {
        private QualificationProcessDAO qualificationProcessDAO;
        private UpdateQualificationProcessUseCase updateQualificationProcessUseCase;

        public InactivateQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO, UpdateQualificationProcessUseCase updateQualificationProcessUseCase){
            this.qualificationProcessDAO = qualificationProcessDAO;
            this.updateQualificationProcessUseCase = updateQualificationProcessUseCase;
        }

        public boolean inactivateQualificationProcess(Long id){
            QualificationProcess qualification = qualificationProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));

            qualification.setRegistrationStatus(RegistrationStatus.INACTIVE);

            return updateQualificationProcessUseCase.update(qualification.getId(), qualification);
        }
}
