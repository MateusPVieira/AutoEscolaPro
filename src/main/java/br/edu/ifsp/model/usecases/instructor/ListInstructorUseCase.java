package br.edu.ifsp.model.usecases.instructor;

import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.dao.InstructorDAO;

import java.util.List;
import java.util.Optional;

public class ListInstructorUseCase {
    private InstructorDAO instructorDAO;

    public ListInstructorUseCase(InstructorDAO instructorDAO){
        this.instructorDAO = instructorDAO;
    }

    public Optional<Instructor> findOne(Long id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null!");
        return instructorDAO.findOne(id);
    }
    public List<Instructor> findAll(){
        return instructorDAO.findAll();
    }

    /*public Optional<Instructor> findOneByCpf(String cpf){
        if(Validator.nullOrEmpty(cpf))
            throw new IllegalArgumentException("CPF can not be null or empty.");
        return instructorDAO.findOneByCPF(cpf);
    }

    public Optional<Instructor> findOneByRg(String rg){
        if(Validator.nullOrEmpty(rg))
            throw new IllegalArgumentException("RG can not be null or empty.");
        return instructorDAO.findOneByCPF(rg);
    }

    public Optional<Instructor> findOneByCnh(String cnh){
        if(Validator.nullOrEmpty(cnh))
            throw new IllegalArgumentException("CNH can not be null or empty.");
        return instructorDAO.findOneByCPF(cnh);
    }

    public Optional<Instructor> findOneByEmail(String email){
        if(Validator.nullOrEmpty(email))
            throw new IllegalArgumentException("E-mail can not be null or empty.");
        return instructorDAO.findOneByCPF(email);
    }*/

    // Igual a do Student, só que pro Instructor
}