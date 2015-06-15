/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery;

import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import org.hibernate.Session;


/**
 *
 * @author János
 */

public class Main {
    public static void main(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        User user = new User();
        user.setUserName("admin");
        user.setEmail("admin@gmail.com");
        user.setPasswordHash("doge");
        user.setFirstName("Admin");
        user.setLastName("András");
        
        
        
        session.persist(user);
    }
}
