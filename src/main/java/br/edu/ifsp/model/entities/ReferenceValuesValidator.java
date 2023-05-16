package br.edu.ifsp.model.entities;

public class ReferenceValuesValidator extends Validator<ValuesReference>{
    @Override
    public Notification validate(ValuesReference valuesReference) {
        Notification notification = new Notification();
        if (valuesReference == null){
            notification.addError("Student is null!");
        }
        if(nullOrEmpty(String.valueOf(valuesReference.getDefaultMinimunNumberOfLessons())) || valuesReference.getDefaultMinimunNumberOfLessons() < 0)
            notification.addError("Name is null, empty or invalid!");
        if(nullOrEmpty(String.valueOf(valuesReference.getLessonValueInCents())) || valuesReference.getLessonValueInCents() < 0)
            notification.addError("Name is null, empty or invalid!");
        if(nullOrEmpty(String.valueOf(valuesReference.getTestValueInCents())) || valuesReference.getTestValueInCents() < 0)
            notification.addError("Name is null, empty or invalid!");

        return notification;
    }
}
