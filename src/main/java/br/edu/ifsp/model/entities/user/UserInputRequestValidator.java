package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.Validator;

public class UserInputRequestValidator extends Validator<User> {
    @Override
    public Notification validate(User user) {
        Notification notification = new Notification();
        if (user == null){
            notification.addError("User is null");
            return notification;
        }
        if(nullOrEmpty(user.getName())){
            notification.addError("Name is null or empty");
        }
        if(nullOrEmpty(user.getUsername())){
            notification.addError("Username is null or empty");
        }
        if(nullOrEmpty(user.getPassword())){
            notification.addError("Password is null or empty");
        }
        if(nullOrEmpty(user.getEmail())){
            notification.addError("Email is null or empty");
        }
        if(nullOrEmpty(user.getPhone())){
            notification.addError("Phone is null or empty");
        }
        if(nullOrEmpty(user.getName())){
            notification.addError("Name is null or empty");
        }






        return notification;
    }
}
