package br.edu.ifsp.model.validators;

import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.entities.instructor.Instructor;

public class InstructorInputRequestValidator extends Validator<Instructor> {

    @Override
    public Notification validate(Instructor instructor) {
        Notification notification = new Notification();
        if (instructor == null){
            notification.addError("Instructor is null!");
        }
        if(nullOrEmpty(instructor.getName()))
            notification.addError("Name is null or empty!");
        if(nullOrEmpty(instructor.getCpf()))
            notification.addError("CPF is null or empty!");
        if(nullOrEmpty(instructor.getRg()))
            notification.addError("RG is null or empty!");
        if(nullOrEmpty(instructor.getCnh()))
            notification.addError("CNH is null or empty!");
        if(nullOrEmpty(instructor.getAddress()))
            notification.addError("Adress is null or empty!");
        if(nullOrEmpty(instructor.getPhone()))
            notification.addError("Phone is null or empty!");
        /*if(nullOrEmpty(instructor.getEmail()))
            notification.addError("E-mail is null or empty!");*/

        return notification;
    }
}