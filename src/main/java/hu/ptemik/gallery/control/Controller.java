/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.control;

import hu.ptemik.gallery.dto.Picture;
import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.derby.client.am.SqlException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author János
 */
public class Controller {

    public static List<User> queryUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query getUsers = session.createQuery("from User");
        List<User> users = getUsers.list();
//        System.out.println("Number of useres found: "+ users.size());
        session.close();
        return users;
    }
    
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
    
    public static void newUser(User user) throws SqlException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        if (user !=null){
           session.beginTransaction();
           session.persist(user);
        }
        session.getTransaction().commit();
        session.close();
    }
    
    public static void newPicture(Picture pic, User user) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (user !=null && pic != null){
           user.addPicture(pic);
           pic.setUser(user);
           session.beginTransaction();
           
//           session.persist(user);
           session.persist(pic);
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
    
    public static List<Picture> queryPictures(int userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Picture p where p.USER_ID = :user");
        query.setParameter("user", userId);
        List pictures =  query.list();
        
        session.close();
        return pictures;
    }
    
    public static List<Picture> queryPictures(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Picture");
        List pictures = query.list();
        
        session.close();
        return pictures;
    }
    
}
