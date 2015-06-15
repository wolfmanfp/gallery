/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery;

import hu.ptemik.gallery.control.Encrypt;
import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author János
 */
public class TestForReal {
    private static SessionFactory sessionFactory;
    private Session session;
    
    @BeforeClass
    public static void setUpClass() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Before
    public void setUp() {
        session = sessionFactory.openSession();
    }
    
    @Test    
    public void createUserTest() {
        try {
            session.getTransaction().begin();
            User user = new User();
            user.setFirstName("Péter");
            user.setLastName("Farkas");
            user.setPasswordHash(Encrypt.encrypt("lolololol"));
            user.setUserName("wolfman");
            user.setEmail("asd@posta.hu");
            
            session.save(user);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        session.close();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        sessionFactory.close();
    }
}
