package hu.ptemik.gallery.entities;

import hu.ptemik.gallery.util.Encrypt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author János
 */
@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Getter
    @Setter
    @Column(name = "USERNAME", unique = true)
    @NotNull
    private String userName;

    @Getter
    @Column(name = "PASSWORD")
    @NotNull
    private String passwordHash;

    @Setter
    @Column(name = "EMAIL", unique = true)
    @NotNull
    private String email;

    @Getter
    @Setter
    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstName;

    @Getter
    @Setter
    @Column(name = "LAST_NAME")
    @NotNull
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Picture> pictures = new ArrayList<>();

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
