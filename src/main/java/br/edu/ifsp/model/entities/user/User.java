package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;

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

    private RegistrationStatus registrationStatus;

    public User() {
    }

    public User(int id, String name, String username, String password, String email, String phone, AcessLevel acessLevel, RegistrationStatus registrationStatus) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.acessLevel = acessLevel;
        this.registrationStatus = registrationStatus;
    }

    public User(int id, String name, String username, String email, String phone, AcessLevel acessLevel, RegistrationStatus registrationStatus) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.acessLevel = acessLevel;
        this.registrationStatus = registrationStatus;
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

    //Não estavam no diagrama de classes e são necessário para ativar/inativar o usuário.
    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPasswordTips(List<String> passwordTips) {
        this.passwordTips = passwordTips;
    }

    public void setAcessLevel(AcessLevel acessLevel) {
        this.acessLevel = acessLevel;
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
