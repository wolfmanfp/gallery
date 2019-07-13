package hu.ptemik.gallery.entities;

import hu.ptemik.gallery.util.Encrypt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author János
 */
@Entity
@Table (name = "USERS")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int id;

    @Getter @Setter
    @Column (name = "USERNAME" , unique = true, nullable = false, length = 50)
    private String userName;

    @Getter
    @Column (name = "PASSWORD", nullable = false, length = 50)
    private String passwordHash;

    @Setter
    @Column (name = "EMAIL", unique = true, nullable = false, length = 50)
    private String email;

    @Getter @Setter
    @Column (name = "FIRST_NAME", nullable = false, length = 25)
    private String firstName;

    @Getter @Setter
    @Column (name = "LAST_NAME", nullable = false, length = 25)
    private String lastName;

    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Picture> pictures = new ArrayList<>();

    public User(String userName, String passwordHash, String email, String firstName, String lastName) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = Encrypt.encrypt(passwordHash);
    }

    public void addPicture(Picture pic) {
        pictures.add(pic);
    }

}
