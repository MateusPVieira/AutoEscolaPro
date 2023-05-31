package br.edu.ifsp.application.view.model.dao;

import br.edu.ifsp.application.view.model.entities.category.DrivingCategory;
import br.edu.ifsp.application.view.model.entities.reference.ValuesReference;

import java.util.Optional;

public interface ReferenceValuesDAO extends DAO<ValuesReference, Long> {

    Optional<ValuesReference> findOneByKeycategory(DrivingCategory category);
}
