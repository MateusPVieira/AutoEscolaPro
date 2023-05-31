package br.edu.ifsp.application.view.model.dao;

import br.edu.ifsp.application.view.model.entities.schedule.Cancellation;

import java.util.Optional;

public interface CancellationDAO extends DAO<Cancellation, Long>{
    Optional<Cancellation> findOneById(Long id);
}
