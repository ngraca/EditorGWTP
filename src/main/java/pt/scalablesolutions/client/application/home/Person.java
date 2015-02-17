package pt.scalablesolutions.client.application.home;

import pt.scalablesolutions.client.validation.PasswordsValidator;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@PasswordsValidator.PasswordsEqual(fields = {"password", "passwordVerify"})
public class Person {

    @NotNull
    @Size(min = 4, message = "Name must be at least 4 characters long.")
    private String name;

    private String password;

    private String passwordVerify;
    
    @Digits(integer = 3, fraction = 0)
    private Integer age;
    
    private Date birthDate;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
