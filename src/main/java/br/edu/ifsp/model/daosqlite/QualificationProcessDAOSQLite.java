package br.edu.ifsp.model.daosqlite;

import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;

import java.util.List;
import java.util.Optional;

public class QualificationProcessDAOSQLite implements QualificationProcessDAO {
    @Override
    public Long create(QualificationProcess type) {
        return null;
    }

    @Override
    public Optional<QualificationProcess> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Optional<List<QualificationProcess>> findAll() {
        return Optional.empty();
    }

    @Override
    public boolean update(QualificationProcess type) {
        return false;
    }
}
