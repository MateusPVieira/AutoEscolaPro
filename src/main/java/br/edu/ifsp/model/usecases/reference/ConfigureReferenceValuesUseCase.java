package br.edu.ifsp.application.view.model.usecases.reference;

import br.edu.ifsp.application.view.model.entities.category.DrivingCategory;
import br.edu.ifsp.application.view.model.entities.reference.ValuesReference;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.application.view.model.validators.ReferenceValuesValidator;
import br.edu.ifsp.application.view.model.entities.notification.Notification;
import br.edu.ifsp.application.view.model.dao.ReferenceValuesDAO;

/**
 * Class responsible for configuring reference values.
 * This use case provides a method to configure reference values for a specific driving category.
 * It interacts with the ReferenceValuesDAO and ReferenceValuesValidator to access and validate the reference values data.
 * Note: This use case assumes that the reference values for the given driving category exist in the system.
 * If the reference values are not found, an EntityNotFoundException will be thrown.
 *
 * @author Your Name
 */
public class ConfigureReferenceValuesUseCase {
    private ReferenceValuesDAO referenceValuesDAO;
    private ReferenceValuesValidator referenceValuesValidator;

    /**
     * Constructor for the ConfigureReferenceValuesUseCase class.
     *
     * @param referenceValuesDAO The ReferenceValuesDAO object used to access reference values data.
     * @param referenceValuesValidator The ReferenceValuesValidator object used to validate reference values.
     */
    public ConfigureReferenceValuesUseCase(ReferenceValuesDAO referenceValuesDAO, ReferenceValuesValidator referenceValuesValidator) {
        this.referenceValuesDAO = referenceValuesDAO;
        this.referenceValuesValidator = referenceValuesValidator;
    }

    /**
     * Configures the reference values for a specific driving category.
     *
     * @param category The driving category for which the reference values are being configured.
     * @param minimumNumberOfLessons The minimum number of lessons for the driving category.
     * @param lessonsValue The value of each lesson in cents.
     * @param testValue The value of the driving test in cents.
     * @return true if the configuration is successful, false otherwise.
     * @throws EntityNotFoundException If the reference values for the given driving category are not found in the system.
     * @throws IllegalArgumentException If the reference values fail the validation.
     */
    public boolean configureValues(DrivingCategory category, int minimumNumberOfLessons, long lessonsValue, long testValue){
        ValuesReference referenceValues = referenceValuesDAO.findOneByKeycategory(category)
                .orElseThrow(()-> new EntityNotFoundException("Reference Value not found!"));
        referenceValues.setDefaultMinimunNumberOfLessons(minimumNumberOfLessons);
        referenceValues.setLessonValueInCents(lessonsValue);
        referenceValues.setTestValueInCents(testValue);

        Notification notification = referenceValuesValidator.validate(referenceValues);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return referenceValuesDAO.update(referenceValues);
    }
}
