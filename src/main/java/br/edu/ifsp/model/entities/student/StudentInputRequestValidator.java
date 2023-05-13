package br.edu.ifsp.model.entities.student;

import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

public class StudentInputRequestValidator extends Validator<Student> {

    @Override
    public Notification validate(Student student) {
        Notification notification = new Notification();
        if (student == null){
            notification.addError("Student is null!");
        }
        if(nullOrEmpty(student.getName()))
            notification.addError("Name is null or empty!");
        if(nullOrEmpty(student.getCpf()))
            notification.addError("CPF is null or empty!");
        if(nullOrEmpty(student.getRg()))
            notification.addError("RG is null or empty!");
        if(nullOrEmpty(student.getCnh()))
            notification.addError("CNH is null or empty!");
        if(nullOrEmpty(student.getAddress()))
            notification.addError("Adress is null or empty!");
        if(nullOrEmpty(student.getPhone()))
            notification.addError("Phone is null or empty!");
        if(nullOrEmpty(student.getEmail()))
            notification.addError("E-mail is null or empty!");

        return notification;
    }
}
