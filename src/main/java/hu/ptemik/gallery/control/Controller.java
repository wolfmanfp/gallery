package hu.ptemik.gallery.control;

import hu.ptemik.gallery.entities.Picture;
import hu.ptemik.gallery.entities.Picture_;
import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.entities.User_;
import hu.ptemik.gallery.util.Encrypt;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).orderBy(cb.asc(root.get(User_.userName)));

        Query<User> getUsers = session.createQuery(cq);
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
     * Returns a list of Picture objects according to the userName.
     *
     * @param userName
     * @return List<User>
     */
    public static List<Picture> queryPictures(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Picture> cq = cb.createQuery(Picture.class);
        Root<Picture> root = cq.from(Picture.class);
        cq.select(root)
                .where(cb.equal(root.get(Picture_.user).get(User_.userName), userName));

        Query<Picture> query = session.createQuery(cq);
        List<Picture> pictures = query.list();

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

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root)
                .where(cb.equal(root.get(User_.userName), userName));

        Query<User> query = session.createQuery(cq);
        User user = query.getSingleResult();

        session.close();
        return user;
    }

    /**
     * Finds out if a UserName already exists or not.
     *
     * @param userName String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root)
                .where(cb.equal(root.get(User_.userName), userName));
        Query<User> query = session.createQuery(cq);

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

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root)
                .where(cb.equal(root.get(User_.email), email));

        Query<User> query = session.createQuery(cq);

        if (!query.list().isEmpty()) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
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
