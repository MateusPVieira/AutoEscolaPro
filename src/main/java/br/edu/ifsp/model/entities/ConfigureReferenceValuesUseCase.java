package br.edu.ifsp.model.entities;

public class ConfigureReferenceValuesUseCase {
    private ReferenceValuesDAO referenceValuesDAO;
    private ReferenceValuesValidator referenceValuesValidator;

    public ConfigureReferenceValuesUseCase(ReferenceValuesDAO referenceValuesDAO, ReferenceValuesValidator referenceValuesValidator) {
        this.referenceValuesDAO = referenceValuesDAO;
        this.referenceValuesValidator = referenceValuesValidator;
    }

    public boolean configureValues(DrivingCategory category, int minimumNumberOfLessons, long lessonsValue, long testValue){
      ValuesReference referenceValues = referenceValuesDAO.findOneByKeycategory(category)
                                        .orElseThrow(()-> new EntityNotFoundException("Reference Value not found!"));
      referenceValues.setDefaultMinimunNumberOfLessons(minimumNumberOfLessons);
      referenceValues.setLessonValueInCents(lessonsValue);
      referenceValues.setTestValueInCents(testValue);

      //adaptação do validator
        Notification notification = referenceValuesValidator.validate(referenceValues);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

      return referenceValuesDAO.update(referenceValues);
    }
}
