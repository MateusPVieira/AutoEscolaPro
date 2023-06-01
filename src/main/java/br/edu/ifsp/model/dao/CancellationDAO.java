package br.edu.ifsp.model.dao;

import br.edu.ifsp.model.entities.schedule.Cancellation;

import java.util.Optional;

public interface CancellationDAO extends DAO<Cancellation, Long>{
    Optional<Cancellation> findOneById(Long id);
}
