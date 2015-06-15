/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author János
 */
@Entity
@Table(name = "PICTURES")
public class Picture implements Serializable {
    @Id
    @Column(name = "PICTURE_ID")
    @GeneratedValue
    private int pictureId;
    @ManyToOne
    private User user;
    @Column(name="TITLE")
    private String title;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="URL")
    private String url;

    public Picture() {
    }

    
    public Picture(int pictureId, User user, String title, String description, String url) {
        this.pictureId = pictureId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.url = url;
    }
    
    //Getters

    public int getPictureId() {
        return pictureId;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
    
    //Setter

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
