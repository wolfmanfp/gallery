/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery;

import hu.ptemik.gallery.control.Encrypt;
import hu.ptemik.gallery.dto.Picture;
import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
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
    
    @Test
    public void createPicturesTest(){
       try {
            session.getTransaction().begin();
            User user = new User();
            user.setFirstName("Doge");
            user.setLastName("d");
            user.setPasswordHash(Encrypt.encrypt("asdasdasd"));
            user.setUserName("doge");
            user.setEmail("doge@posta.hu");
            
            Picture pic = new Picture();
            pic.setTitle("Cool'n'The Gang");
            pic.setDescription("Such picture");
            pic.setUser(user);
            pic.setUrl("www.doge.com/swagie");
            
            Picture pic2 = new Picture();
            pic2.setTitle("Cool'n'The Gang2");
            pic2.setDescription("Such picture2");
            pic2.setUser(user);
            pic2.setUrl("www.doge.com/swagie2");
            
            user.addPicture(pic);
            
            session.save(user);
            session.save(pic);
            session.save(pic2);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @Test
    public void selects(){
        List<Picture> pictures = new ArrayList<>();
        Query query = session.createQuery("from Picture");
        pictures =  query.list();
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
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
