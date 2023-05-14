package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;
import br.edu.ifsp.model.entities.student.StudentDAO;

public class UpdateQualificationProcessUseCase {
    private StudentDAO studentDAO;
    private QualificationProcessDAO qualificationProcessDAO;

    public UpdateQualificationProcessUseCase(StudentDAO studentDAO, QualificationProcessDAO qualificationProcessDAO){
        this.studentDAO = studentDAO;
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
