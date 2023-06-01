package br.edu.ifsp.model.dao;

import br.edu.ifsp.model.entities.instructor.Instructor;

import java.util.Optional;

public interface InstructorDAO extends DAO<Instructor, Long> {
    Optional<Instructor> findOneByCPF(String cpf);
    /*Optional<Instructor> findOneByEmail(String email);*/
    Optional<Instructor> findOneByRG(String rg);
    Optional<Instructor> findOneByCNH(String cnh);
}