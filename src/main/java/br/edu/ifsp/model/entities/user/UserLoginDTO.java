package br.edu.ifsp.model.entities.user;

import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;

import java.util.List;

public class UserLoginDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private List<String> passwordTips;
    private AcessLevel acessLevel;
    private RegistrationStatus registrationStatus;

    public UserLoginDTO() {
    }

    public UserLoginDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.passwordTips = user.getPasswordTips();
        this.acessLevel = user.getAcessLevel();
        this.registrationStatus = user.getRegistrationStatus();
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

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public User toUser(){
        return new User(
                this.getId(),
                this.getName(),
                this.getUsername(),
                this.getEmail(),
                this.getPhone(),
                this.getAcessLevel(),
                this.getRegistrationStatus());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passwordTips=" + passwordTips +
                ", acessLevel=" + acessLevel +
                '}';
    }
}
