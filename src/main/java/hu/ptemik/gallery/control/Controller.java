package hu.ptemik.gallery.control;

import hu.ptemik.gallery.entities.Picture;
import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.util.Encrypt;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.query.Query;
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
        Query getUsers = session.createQuery("from User");
        List<User> users = getUsers.list();
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
     * Returns a list of Picture objects according to the User object.
     *
     * @param user user
     * @return List<User>
     */
    public static List<Picture> queryPictures(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Picture where user = :user");
        query.setParameter("user", user);
        List<Picture> pictures = query.list();

        session.close();
        return pictures;
    }

    /**
     * Returns a list of Picture objects according to the userId.
     *
     * @param userName int
     * @return List<User>
     */
    public static List<Picture> queryPictures(String userName) {
        List<Picture> pictures = queryPictures(findUser(userName));
        return pictures;
    }

    /**
     * Finds a User according to userId
     *
     * @param userName
     * @return Returns the User Object.
     */
    public static User findUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        Query query = session.createQuery("from User where userName = :userName");
        query.setParameter("userName", userName);
        user = (User) query.list().get(0);

        session.close();
        return user;
    }

    /**
     * Finds out if a UserName already exist or not.
     *
     * @param userName String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where userName = :userName");
        query.setParameter("userName", userName);

        if (!query.list().isEmpty()) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }

    /**
     * Finds out if a Email already exist or not.
     *
     * @param email String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);

        if (!query.list().isEmpty()) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }

    /**
     * Returns a full list of Picture objects.
     *
     * @return List<Picture>
     */
    public static List<Picture> queryPictures() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Picture");
        List pictures = query.list();

        session.close();
        return pictures;
    }

    /**
     * Deletes a Picture according to pictureId.
     *
     * @param pictureId
     * @return Returns true if successful, false if not.
     */
    public static boolean deletePicture(int pictureId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("delete Picture where id = :pictureId");
            query.setParameter("pictureId", pictureId);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception ex) {
            session.close();
            return false;
        }
    }

    /**
     * Delets a Picture according to Picture object
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
     * Delets a User according to User object
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
