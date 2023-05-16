package br.edu.ifsp.model.entities;

import java.util.Optional;

public interface ReferenceValuesDAO extends DAO<ValuesReference, Long>{

    Optional<ValuesReference> findOneByKeycategory(DrivingCategory category);
}
