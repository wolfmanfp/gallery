/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.control;

import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author János
 */
public class Controler {

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
    
    
}
