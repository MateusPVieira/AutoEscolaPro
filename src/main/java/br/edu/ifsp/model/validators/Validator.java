package br.edu.ifsp.application.view.model.validators;

import br.edu.ifsp.application.view.model.entities.notification.Notification;

import java.util.Collection;

public abstract class Validator <T> {
    public abstract Notification validate (T type);

    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

    public static boolean nullOrEmpty(Collection collection){
        return collection == null || collection.isEmpty();
    }


}
