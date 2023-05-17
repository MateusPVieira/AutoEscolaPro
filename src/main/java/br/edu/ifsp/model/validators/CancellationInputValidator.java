package br.edu.ifsp.model.validators;

import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.entities.schedule.Cancellation;

public class CancellationInputValidator extends Validator<Cancellation> {
    @Override
    public Notification validate(Cancellation cancellation) {
        Notification notification = new Notification();
        if (cancellation == null){
            notification.addError("Cancellation is null!");
        }
        if(nullOrEmpty(cancellation.getReason()))
            notification.addError("Reason is null or empty!");
        if(nullOrEmpty(String.valueOf(cancellation.getFineValueInCents())))
            notification.addError("Fine is null or empty!");
        if(nullOrEmpty(cancellation.getSchedule().toString()))
            notification.addError("Lesson is null or empty!");
        return notification;
    }
}
