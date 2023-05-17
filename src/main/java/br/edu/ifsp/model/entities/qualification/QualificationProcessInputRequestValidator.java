package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;

public class QualificationProcessInputRequestValidator extends Validator<QualificationProcess> {
    @Override
    public Notification validate(QualificationProcess qualificationProcess) {
        Notification notification = new Notification();
        if (qualificationProcess == null){
            notification.addError("QualificationProcess is null!");
        }
        if(nullOrEmpty(String.valueOf(qualificationProcess.getEyeExam())))
            notification.addError("Eye Exam is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getPsychoExam())))
            notification.addError("Psycho Exam is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getTheoricExam())))
            notification.addError("Theoric Exam is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getStudent())))
            notification.addError("Student is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getInstructor())))
            notification.addError("Instructor is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getUser())))
            notification.addError("User is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getDrivingCategory())))
            notification.addError("Driving Category is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getQualificationValueCents())))
            notification.addError("Qualification Value is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getMinimumNumberOfLessons())))
            notification.addError("Minimum Number of Lessons is null or empty!");
        if(nullOrEmpty(String.valueOf(qualificationProcess.getRegistrationStatus())))
            notification.addError("Driving Category is null or empty!");

        return notification;
    }
}
