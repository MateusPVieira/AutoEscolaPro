package br.edu.ifsp.model.usecases.instructor;

import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.validators.Validator;

import java.util.List;
import java.util.Optional;

/**
 * Class responsible for handling the use case of retrieving instructor data.
 * It interacts with the InstructorDAO to perform database operations.
 * @author Stefhani Alkin
 */
public class ListInstructorUseCase {
    private InstructorDAO instructorDAO;

    /**
     * Constructs a ListInstructorUseCase object with the specified InstructorDAO.
     *
     * @param instructorDAO the data access object for instructors
     */
    public ListInstructorUseCase(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    /**
     * Retrieves an instructor by its ID.
     *
     * @param id the ID of the instructor to retrieve
     * @return an Optional object containing the instructor, or an empty Optional if not found
     * @throws IllegalArgumentException if the provided ID is null
     */
    public Optional<Instructor> findOne(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null!");
        return instructorDAO.findOne(id);
    }

    /**
     * Retrieves all instructors.
     *
     * @return a list of all instructors in the system
     */
    public Optional<List<Instructor>> findAll() {
        return instructorDAO.findAll();
    }

    public Optional<Instructor> findOneByCpf(String cpf) {
        if (Validator.nullOrEmpty(cpf))
            throw new IllegalArgumentException("CPF can not be null or empty.");
        return instructorDAO.findOneByCPF(cpf);
    }

    public Optional<Instructor> findOneByRg(String rg) {
        if (Validator.nullOrEmpty(rg))
            throw new IllegalArgumentException("RG can not be null or empty.");
        return instructorDAO.findOneByRG(rg);
    }

    public Optional<Instructor> findOneByCnh(String cnh) {
        if (Validator.nullOrEmpty(cnh))
            throw new IllegalArgumentException("CNH can not be null or empty.");
        return instructorDAO.findOneByCNH(cnh);
    }
}