package hu.ptemik.gallery.control;

import com.querydsl.jpa.hibernate.HibernateQuery;
import hu.ptemik.gallery.entities.Picture;
import hu.ptemik.gallery.entities.QPicture;
import hu.ptemik.gallery.entities.QUser;
import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.util.Encrypt;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author János
 */
public class Controller {

    /**
     * Returns a list of all the User objects in the database.
     *
     * @return
     */
    public static List<User> queryUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QUser user = QUser.user;
        List<User> users = new HibernateQuery<>(session).select(user)
                .from(user)
                .orderBy(user.userName.asc())
                .fetch();

        session.close();
        return users;
    }

    /**
     * Checks if the userName and password given in its parameters is or is not
     * a valid userName-password combination. If it there is a match it returns
     * the User object. If not it returns null.
     *
     * @param userName String.
     * @param pw String
     * @return
     */
    public static User submitLogin(String userName, String pw) {
        String password = Encrypt.encrypt(pw);

        List<User> users = queryUsers();
        for (User user : users) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPasswordHash())) {
                return user;
            }

        }
        return null;
    }

    /**
     * Registers a new User.
     *
     * @param user Needs a User object parameter.
     * @return
     * @throws SQLException
     */
    public static boolean newUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            if (user != null) {
                session.getTransaction().begin();
                session.save(user);
                session.getTransaction().commit();
                session.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (session.isOpen())
                session.close();
            return false;
        }
    }

    /**
     * Registers a new Picture record.
     *
     * @param pic The url of the picture.
     * @param user The uploader.
     * @return True if successful, false if not.
     */
    public static boolean newPicture(Picture pic, User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            if (user != null && pic != null) {
                user.addPicture(pic);
                pic.setUser(user);
                session.beginTransaction();
                session.persist(pic);
                session.getTransaction().commit();
                session.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            if (session.isOpen())
                session.close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Returns a list of Picture objects according to the userName.
     *
     * @param userName
     * @return List<User>
     */
    public static List<Picture> queryPictures(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QPicture picture = QPicture.picture;
        List<Picture> pictures = new HibernateQuery<>(session).select(picture)
                .from(picture)
                .where(picture.user.userName.eq(userName))
                .fetch();

        session.close();
        return pictures;
    }

    /**
     * Finds a User according to userName.
     *
     * @param userName
     * @return Returns the User object.
     */
    public static User findUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QUser qUser = QUser.user;
        User user = new HibernateQuery<>(session).select(qUser)
                .from(qUser)
                .where(qUser.userName.eq(userName))
                .fetchOne();

        session.close();
        return user;
    }

    /**
     * Finds a picture by its ID.
     *
     * @param id
     * @return
     */
    public static Picture findPicture(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QPicture qPicture = QPicture.picture;
        Picture picture = new HibernateQuery<>(session).select(qPicture)
                .from(qPicture)
                .where(qPicture.id.eq(id))
                .fetchOne();

        session.close();
        return picture;
    }

    /**
     * Finds out if a UserName already exists or not.
     *
     * @param userName String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QUser qUser = QUser.user;
        User user = new HibernateQuery<>(session).select(qUser)
                .from(qUser)
                .where(qUser.userName.eq(userName))
                .fetchOne();

        session.close();
        return user != null;
    }

    /**
     * Finds out if a Email already exist or not.
     *
     * @param email String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QUser qUser = QUser.user;
        User user = new HibernateQuery<>(session).select(qUser)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();

        session.close();
        return user != null;
    }

    /**
     * Deletes a Picture according to Picture object
     *
     * @param pic Picture
     * @return Returns true if successful, false if not.
     */
    public static boolean deletePicture(Picture pic) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.delete(pic);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception ex) {
            if (session.isOpen())
                session.close();
            return false;
        }
    }

    /**
     * Deletes a User according to User object
     *
     * @param user User
     * @return Returns true if successful, false if not.
     */
    public static boolean deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception ex) {
            if (session.isOpen())
                session.close();
            return false;
        }
    }
}
