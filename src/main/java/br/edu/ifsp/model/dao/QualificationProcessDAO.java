package br.edu.ifsp.model.dao;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;

import java.util.Optional;

public interface QualificationProcessDAO extends DAO<QualificationProcess, Long> {
    public Optional<QualificationProcess> findByStudentId(Long id);
}
