package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;

import java.util.Collection;

public class ListQualificationProcessUseCase {

    private QualificationProcessDAO qualificationProcessDAO;
    public ListQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO){
        this.qualificationProcessDAO = qualificationProcessDAO;
    }

    public QualificationProcess findOne(Long id){
        return qualificationProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));
    }

    public Collection<QualificationProcess> findSome(){
        return qualificationProcessDAO.findAll();
    }
}
