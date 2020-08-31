package hu.ptemik.gallery.service;

import com.querydsl.jpa.hibernate.HibernateQuery;
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
public class UserService {

    /**
     * Returns a list of all the User objects in the database.
     *
     * @return
     */
    public List<User> queryUsers() {
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
    public User submitLogin(String userName, String pw) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String password = Encrypt.encrypt(pw);

        QUser qUser = QUser.user;
        User user = new HibernateQuery<>(session).select(qUser)
                .from(qUser)
                .where(qUser.userName.eq(userName))
                .fetchOne();

        if (user.getPasswordHash().equals(password)) {
            session.close();
            return user;
        }
        session.close();
        return null;
    }

    /**
     * Registers a new User.
     *
     * @param user Needs a User object parameter.
     * @return
     * @throws SQLException
     */
    public boolean newUser(User user) {
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
            if (session.isOpen()) {
                session.close();
            }
            return false;
        }
    }

    /**
     * Finds a User according to userName.
     *
     * @param userName
     * @return Returns the User object.
     */
    public User findUser(String userName) {
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
     * Finds out if a Email already exist or not.
     *
     * @param email String
     * @return Returns true if exists, false if not.
     */
    public boolean isExistingEmail(String email) {
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
     * Finds out if a UserName already exists or not.
     *
     * @param userName String
     * @return Returns true if exists, false if not.
     */
    public boolean isExistingUser(String userName) {
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
     * Deletes a User according to User object
     *
     * @param user User
     * @return Returns true if successful, false if not.
     */
    public boolean deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception ex) {
            if (session.isOpen()) {
                session.close();
            }
            return false;
        }
    }
}
