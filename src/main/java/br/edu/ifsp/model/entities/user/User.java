package br.edu.ifsp.model.entities.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private List<String> passwordTips = new ArrayList<String>();
    private AcessLevel acessLevel;

    public User() {
    }

    public User(int id, String name, String username, String password, String email, String phone, AcessLevel acessLevel) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.acessLevel = acessLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getPasswordTips() {
        return passwordTips;
    }

    public AcessLevel getAcessLevel() {
        return acessLevel;
    }
   // add and remove from list
    public void addPasswordTip(String passwordTip){
       this.passwordTips.add(passwordTip);
    }

    public void removePasswordTip(String passwordTip){
        this.passwordTips.remove(passwordTip);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passwordTips=" + passwordTips +
                ", acessLevel=" + acessLevel +
                '}';
    }


}
