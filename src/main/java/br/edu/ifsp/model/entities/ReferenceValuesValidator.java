package br.edu.ifsp.model.entities;

public class ReferenceValuesValidator extends Validator<ValuesReference>{
    @Override
    public Notification validate(ValuesReference valuesReference) {
        Notification notification = new Notification();
        if (valuesReference == null){
            notification.addError("Student is null!");
        }
        if(nullOrEmpty(String.valueOf(valuesReference.getDefaultMinimunNumberOfLessons())))
            notification.addError("Name is null or empty!");
        if(nullOrEmpty(String.valueOf(valuesReference.getLessonValueInCents())))
            notification.addError("CPF is null or empty!");
        if(nullOrEmpty(String.valueOf(valuesReference.getTestValueInCents())))
            notification.addError("RG is null or empty!");

        return notification;
    }
}
