package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.validators.Validator;

import java.util.List;
import java.util.Optional;

/**
 * Class responsible for handling the use case of retrieving student data.
 * It interacts with the StudentDAO to perform database operations.
 * @author Stefhani Alkin
 */
public class ListStudentUseCase {
    private StudentDAO studentDAO;

    /**
     * Constructs a ListStudentUseCase object with the specified StudentDAO.
     *
     * @param studentDAO the data access object for students
     */
    public ListStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to retrieve
     * @return an Optional object containing the student, or an empty Optional if not found
     * @throws IllegalArgumentException if the provided ID is null
     */
    public Optional<Student> findOne(Long id){
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null!");
        return studentDAO.findOne(id);
    }

    /**
     * Retrieves all students.
     *
     * @return a list of all students in the system
     */
    public Optional<List<Student>> findAll(){
        return studentDAO.findAll();
    }

    public Optional<Student> findOneByCpf(String cpf){
        if(Validator.nullOrEmpty(cpf))
            throw new IllegalArgumentException("CPF can not be null or empty.");
        return studentDAO.findOneByCPF(cpf);
    }
}
    // findAll //
    // Retorna uma lista de todos os estudantes cadastrados no sistema.

/*
    public Optional<Student> findOneByRg(String rg){
        if(Validator.nullOrEmpty(rg))
            throw new IllegalArgumentException("RG can not be null or empty.");
        return studentDAO.findOneByCPF(rg);
    }

    public Optional<Student> findOneByCnh(String cnh){
        if(Validator.nullOrEmpty(cnh))
            throw new IllegalArgumentException("CNH can not be null or empty.");
        return studentDAO.findOneByCPF(cnh);
    }

    public Optional<Student> findOneByEmail(String email){
        if(Validator.nullOrEmpty(email))
            throw new IllegalArgumentException("E-mail can not be null or empty.");
        return studentDAO.findOneByCPF(email);
    }*/
