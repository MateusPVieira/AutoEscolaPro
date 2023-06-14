package br.edu.ifsp.model.dao;

import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.entities.reference.ValuesReference;

import java.util.Optional;

public interface ReferenceValuesDAO extends DAO<ValuesReference, Long> {

    Optional<ValuesReference> findOneByKeycategory(String category);
}
