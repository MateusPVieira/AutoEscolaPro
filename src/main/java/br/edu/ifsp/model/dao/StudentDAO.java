package br.edu.ifsp.model.dao;

import br.edu.ifsp.model.entities.student.Student;

import java.util.Optional;

public interface StudentDAO extends DAO<Student, Long> {
    Optional<Student> findOneByCPF(String cpf);
    Optional<Student> findOneByEmail(String email);
    Optional<Student> findOneByRG(String rg);
    Optional<Student> findOneByCNH(String cnh);

}
