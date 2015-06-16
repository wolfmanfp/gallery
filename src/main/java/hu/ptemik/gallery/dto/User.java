/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author János
 */
@Entity
@Table (name  = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int id;
    
    @Column (name ="USERNAME" , unique = true, nullable = false, length = 15)
    private String userName;
    @Column (name  = "PASSWORD", nullable = false, length = 50)
    private String passwordHash;
    @Column (name  = "EMAIL", unique = true, nullable = false, length = 50)
    private String email;
    @Column (name  = "FIRST_NAME", nullable = false, length = 25)
    private String firstName;
    @Column (name  = "LAST_NAME", nullable = false, length = 25)
    private String lastName;

    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
//    @JoinTable(name="USER_PICTURES", joinColumns = @JoinColumn(name="ID"),
//            inverseJoinColumns = @JoinColumn(name="PICTURE_ID"))
    
    private Collection<Picture> pictures = new ArrayList<>();
    
    public User() {
    }

    public User(int id, String userName, String passwordHash, String email, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addPicture(Picture pic) {
        pictures.add(pic);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", passwordHash=" + passwordHash + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", pictures=" + pictures + '}';
    }
    
    
}
