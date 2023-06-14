package br.edu.ifsp.application.main;

import br.edu.ifsp.application.controller.LoginUIController;
import br.edu.ifsp.application.repository.DatabaseBuilder;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.usecases.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    /*public static CreateUserUseCase createUserUseCase;
    public static ActivateUserUseCase activateUserUseCase;
    public static InactivateUserUseCase inactivateUserUseCase;
    public static ListUserUseCase listUserUseCase;
    public static UpdateUserUseCase updateUserUseCase;*/

    public static void main(String[] args) {
        setupDatabase();
        WindowLoader.main(args);

    }

    public static void setupDatabase(){
        DatabaseBuilder databaseBuilder =  new DatabaseBuilder();
        databaseBuilder.buildDatabaseIfMissing();
    }




}
/*Ixpludiuuuuu*/