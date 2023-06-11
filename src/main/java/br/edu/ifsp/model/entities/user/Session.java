package br.edu.ifsp.model.entities.user;

public class Session {
    private static Session instance = null;
    private UserLoginDTO user;

    private Session(){

    }

    public static Session getInstance(){
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public void setUser(UserLoginDTO user){
        this.user = user;
    }

    public UserLoginDTO getUser(){
        return user;
    }

    public boolean checkSession(){
        return instance != null && user != null;
    }

}
