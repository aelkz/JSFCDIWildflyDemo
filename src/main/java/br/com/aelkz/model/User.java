package br.com.aelkz.model;

import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class User implements Serializable {

    public enum Gender {
        MALE(1,"MALE"),
        FEMALE(2,"FEMALE");

        private Integer type;
        private String description;

        Gender(Integer type, String description) {
            this.type = type;
            this.description = description;
        }
    };

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;           // input mask
    private String cellphone;       // input mask
    private String gender;          // one radio
    private String linkedin;
    private UploadedFile resume;    // file -> upload

    public User() {}

    public User(Integer id, String firstName, String lastName, String email, String cellphone, String gender, String linkedin, UploadedFile resume) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cellphone = cellphone;
        this.gender = gender;
        this.linkedin = linkedin;
        this.resume = resume;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public UploadedFile getResume() {
        return resume;
    }

    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public User clone() {
        return new User(this.id, this.firstName, this.lastName, this.email, this.cellphone, this.gender, this.linkedin, this.resume);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        return getLastName().equals(user.getLastName());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }
}
