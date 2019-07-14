package hu.ptemik.gallery.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author János
 */
@Entity
@Table(name = "PICTURES")
@NoArgsConstructor
public class Picture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PICTURE_ID")
    private int id;

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
