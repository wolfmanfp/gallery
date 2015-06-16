/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.control;

import hu.ptemik.gallery.dto.Picture;
import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.util.List;
import java.sql.SQLException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author János
 */
public class Controller {

    /**
     * Returns a list of all the User objects in the database.
     * @return 
     */
    public static List<User> queryUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query getUsers = session.createQuery("from User");
        List<User> users = getUsers.list();
//        System.out.println("Number of useres found: "+ users.size());
        session.close();
        return users;
    }
    /**
     * Checks if the userName and password given in its parameters is or is not a valid userName-password combination. 
     * If it there is a match it returns the User object.
     * If not it returns null.
     * @param userName String.
     * @param pw String
     * @return 
     */
    public static User submitLogin(String userName, String pw) {
        String password = Encrypt.encrypt(pw);
        
        List<User> users = queryUsers();

        for (User user : users) {
            if(userName.equals(user.getUserName()) && password.equals(user.getPasswordHash())){
                return user;
            }
            
        }
        return null;
    }
    
    /**
     * Registers a new User. 
     * @param user Needs a User object parameter.
     * @throws SQLException 
     */
    public static void newUser(User user) throws SQLException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        if (user !=null){
           session.getTransaction().begin();
           session.save(user);
           session.getTransaction().commit();
        }
        session.close();
    }
    
    /**
     * Registers a new Picture record.
     * @param pic The url of the picture.
     * @param user The uploader.
     * @throws Exception 
     */
    public static void newPicture(Picture pic, User user) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (user !=null && pic != null){
           user.addPicture(pic);
           pic.setUser(user);
           session.beginTransaction();
           
//           session.persist(user);
           session.persist(pic);
           session.persist(user);
        }
        else if(user==null){
            throw new Exception("Nem adtál meg felhasználót");
        }
        else if(pic == null){
            throw new Exception("Nem adtál meg képet");
        }
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Returns a list of Picture objects according to the cryteria User object.
     * @param user 
     * @return 
     */
    public static List<Picture> queryPictures(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Picture where user = :user");
        query.setParameter("user", user);
        List<Picture> pictures =  query.list();
        
        session.close();
        return pictures;
    }
    
    public static List<Picture> queryPictures(int userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM users WHERE USER_ID = :userId ");
        query.setParameter("userId", userId);
        List<Picture> pictures =  query.list();
        
        session.close();
        return pictures;
    }
    /**
     * Finds a User according to userId
     * @param userId
     * @return Returns the User Object.
     */
    public static User findUser(int userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        Query query = session.createSQLQuery("from User where id = :id");
        query.setParameter("id", userId);
        user =  (User) query.list().get(0);
        
        session.close();
        return user;
    }
    
    /**
     * Finds out if a UserName already exist or not.
     * @param userName String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingUser(String userName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        Query query = session.createSQLQuery("from User where userName = :userName");
        query.setParameter("userName", userName);
        user =  (User) query.list().get(0);
        
        if (user !=null){
            session.close();
            return true;
        }
        else{
            session.close();
            return false;
        }
    }
    
    /**
     * Finds out if a Email already exist or not.
     * @param email String
     * @return Returns true if exists, false if not.
     */
    public static boolean isExistingEmail(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        Query query = session.createSQLQuery("from User where email = :email");
        query.setParameter("email", email);
        user = (User) query.list().get(0);
        
        if (user !=null){
            session.close();
            return true;
        }
        else{
            session.close();
            return false;
        }
    }
    
    /**
     * Returns a full list of Picture objects.
     * @return 
     */
    public static List<Picture> queryPictures(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Picture");
        List pictures = query.list();
        
        session.close();
        return pictures;
    }
    
}
