package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.enums.RegistrationStatus;

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
