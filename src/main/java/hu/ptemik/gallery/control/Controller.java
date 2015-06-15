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
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author JĂˇnos
 */
public class Controller {

    public List<User> queryUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query getUsers = session.createQuery("from User");
        List<User> users = getUsers.list();
//        System.out.println("Number of useres found: "+ users.size());
        session.close();
        return users;
    }
    
    public void submitLogin(String loginName, char[] pw) {
        String password = Encrypt.encrypt(Arrays.toString(pw));
        
        List<User> users = queryUsers();

        for (User user : users) {
            if(loginName.equals(user.getUserName()) && password.equals(user.getPasswordHash())){
                
            }
            else{ 
            }
        }

    }
    
    public void newUser(User user){
         Session session = HibernateUtil.getSessionFactory().openSession();
        if (user !=null){
           session.beginTransaction();
           session.persist(user);
        }
        session.close();
    }
    
    public void newPicture(Picture pic, User user) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        if (user !=null && pic != null){
           user.addPicture(pic);
           session.beginTransaction();
           
           session.persist(user);
           session.persist(pic);
        }
        else if(user==null){
            throw new Exception("Nem adtĂˇl meg felhasznĂˇlĂłt");
        }
        else if(pic == null){
            throw new Exception("Nem adtĂˇl meg kĂ©pet");
        }
        
        session.close();
    }
    
    public List<Picture> queryPictures(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Picture where ");
        List pictures =  query.list();
        
        session.close();
        return pictures;
    }
    
    public List<Picture> queryPictures(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Picture");
        List pictures =  query.list();
        
        session.close();
        return pictures;
    }
    
}
