package hu.ptemik.gallery.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author János
 */
@Entity
@Table(name = "PICTURES")
@NoArgsConstructor
public class Picture implements Serializable {

    @Id
    @Column(name = "PICTURE_ID")
    @GeneratedValue
    private int pictureId;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Getter @Setter
    @Column(name = "TITLE", length = 25)
    private String title;

    @Getter @Setter
    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Getter @Setter
    @Column(name = "URL", unique = true)
    private String url;
    
    public Picture(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

}
