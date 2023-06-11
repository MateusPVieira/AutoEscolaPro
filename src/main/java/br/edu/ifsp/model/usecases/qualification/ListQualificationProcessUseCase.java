package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Class responsible for listing qualification processes.
 * This use case provides methods to retrieve qualification processes from the system.
 * It interacts with the QualificationProcessDAO to access the qualification process data.
 * Note: This use case assumes that the qualification process with the given ID exists in the system.
 * If the qualification process is not found, an EntityNotFoundException will be thrown.
 *
 * @author Mateus Vieira
 */
public class ListQualificationProcessUseCase {

    private QualificationProcessDAO qualificationProcessDAO;

    /**
     * Constructor for the ListQualificationProcessUseCase class.
     *
     * @param qualificationProcessDAO The QualificationProcessDAO object used to access qualification process data.
     */
    public ListQualificationProcessUseCase(QualificationProcessDAO qualificationProcessDAO){
        this.qualificationProcessDAO = qualificationProcessDAO;
    }

    /**
     * Retrieves a qualification process with the given ID.
     *
     * @param id The ID of the qualification process to retrieve.
     * @return The QualificationProcess object with the specified ID.
     * @throws EntityNotFoundException If the qualification process with the given ID is not found in the system.
     */
    public QualificationProcess findOne(Long id){
        return qualificationProcessDAO.findOne(id).orElseThrow(()-> new EntityNotFoundException("Qualification Process not found!"));
    }

    /**
     * Retrieves a collection of qualification processes.
     *
     * @return A collection of QualificationProcess objects representing all the qualification processes in the system.
     */
    public Optional<List<QualificationProcess>> findSome(){
        return qualificationProcessDAO.findAll();
    }
}

