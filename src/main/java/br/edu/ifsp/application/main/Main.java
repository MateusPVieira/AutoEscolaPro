package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.DatabaseBuilder;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.enums.ScheduleType;
import br.edu.ifsp.model.usecases.user.*;


public class Main {

    public static CreateUserUseCase createUserUseCase;
    public static ActivateUserUseCase activateUserUseCase;
    public static InactivateUserUseCase inactivateUserUseCase;
    public static ListUserUseCase listUserUseCase;
    public static UpdateUserUseCase updateUserUseCase;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        setupDatabase();
        WindowLoader.main(args);
    }

    public static void setupDatabase(){
        DatabaseBuilder databaseBuilder =  new DatabaseBuilder();
        databaseBuilder.buildDatabaseIfMissing();
    }




}
/*Ixpludiuuuuu*/