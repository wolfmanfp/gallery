/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery;

import hu.ptemik.gallery.control.Controller;
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
//            session.getTransaction().begin();
            User user = new User();
            user.setFirstName("Péter");
            user.setLastName("Farkas");
            user.setPasswordHash(Encrypt.encrypt("lolololol"));
            user.setUserName("wolfman");
            user.setEmail("asd@posta.hu");

            Controller.newUser(user);

//            session.save(user);
//            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPicturesTest() {
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
    public void selectsPicturesTest1() {
        try{
        List<Picture> pictures ;
        Query query = session.createQuery("from Picture");
        pictures = query.list();
        System.out.println("List of pictures (" + pictures.size() + ")");
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void selectPicturesTest2() {
        try {
            session.getTransaction().begin();
        User user = new User();
        user.setFirstName("Not Doge");
        user.setLastName("Chan");
        user.setPasswordHash(Encrypt.encrypt("Nope"));
        user.setUserName("not_doge");
        user.setEmail("not_doge@posta.hu");

        Picture pic = new Picture();
        pic.setTitle("Electric Avenue");
        pic.setDescription("420");
        pic.setUser(user);
        pic.setUrl("www.420Blazeit.com/swagie");
        
        session.save(user);
        session.save(pic);
        
        session.getTransaction().commit();
        
        List<Picture> pictures = Controller.queryPictures(user);
        System.out.println("List of pictures from user (" + pictures.size() + ")");
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }
        
        System.out.println("Find a User by userId. userId = 2");
        System.out.println(Controller.findUser(2).toString());
 
        }
        catch (Exception ex){
            ex.printStackTrace();
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
