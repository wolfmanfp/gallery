/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.control;

import hu.ptemik.gallery.dto.Picture;
import hu.ptemik.gallery.dto.User;
import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ControllerTest {

    private static SessionFactory sessionFactory;
    private Session session;
    private User user1 = new User();
    private User user2 = new User();
    private Picture pic1 = new Picture();
    private Picture pic2 = new Picture();
    private Picture pic3 = new Picture();

    public ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        sessionFactory = HibernateUtil.getSessionFactory();

    }

    @AfterClass
    public static void tearDownClass() {
//        sessionFactory.close();
    }

    @Before
    public void setUp() {
        session = sessionFactory.openSession();
        user1.setFirstName("Péter");
        user1.setLastName("Farkas");
        user1.setPasswordHash(Encrypt.encrypt("lolololol"));
        user1.setUserName("wolfman");
        user1.setEmail("Y_U_Dudis@spodermun.cum");

        user2.setFirstName("Péter");
        user2.setLastName("János");
        user2.setPasswordHash(Encrypt.encrypt("doge"));
        user2.setUserName("jancsiij");
        user2.setEmail("jancsiij@gmail.com");
        
        pic1.setTitle("Cool'n'The Gang");
        pic1.setDescription("Such picture");
        
        pic1.setUrl("www.doge.com/swagie");

        pic2.setTitle("Cool'n'The Gang2");
        pic2.setDescription("Such picture2");
        
        pic2.setUrl("www.doge.com/swagie2");
        
        pic3.setTitle("#1 Dad");
        pic3.setDescription("#GOT,#Fire,#LordOfLight");
        
        pic3.setUrl("http://img-9gag-fun.9cache.com/photo/aPGwYQg_460s.jpg");
        session.close();
    }

    @After
    public void tearDown() {
        //session.close();
    }

    /**
     * Test of queryUsers method, of class Controller.
     */
    /**
     * Test of newUser method, of class Controller.
     */
    @Test
    public void testNewUser() throws Exception {
        int rand = (int )(Math.random() * 50 + 1);
        System.out.println("newUser");
        User localUser1 = new User("Test"+rand, "test","test"+rand+"@test.t", "Test", "Tamás");
      
        assertTrue(Controller.newUser(localUser1));
        assertFalse(Controller.newUser(localUser1));
        Controller.deleteUser(localUser1);
    }

    /**
     * Test of newPicture method, of class Controller.
     */
    @Test
    public void testNewPicture() throws Exception {
        
        int rand = (int )(Math.random() * 50 + 1);
        System.out.println("newPicture");
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        Picture pic = new Picture("Title", "Desc", "randomUrl"+rand+".com");
        
        Controller.newUser(localUser1);
   
        assertTrue(Controller.newPicture(pic, localUser1));
        assertFalse(Controller.newPicture(pic, localUser1));
        Controller.deletePicture(pic);
        Controller.deleteUser(localUser1);
    }
    
    @Test
    public void testQueryUsers() {
        int rand = (int )(Math.random() * 50 + 1);
        System.out.println("queryUsers");
        int numberOfUsers = Controller.queryUsers().size();
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        
        Controller.newUser(localUser1);
        assertTrue(Controller.queryUsers().size() == numberOfUsers + 1);
        Controller.deleteUser(localUser1);
    }

    /**
     * Test of submitLogin method, of class Controller.
     */
    @Test
    public void testSubmitLogin() {
        System.out.println("submitLogin");
        
        int rand = (int )(Math.random() * 500 + 1);
        User localUser1 = new User("Test"+rand, Encrypt.encrypt("test"),"test"+rand+"@test.t", "Test", "Tamás");
        Controller.newUser(localUser1);
        
        assertNotNull(Controller.submitLogin(localUser1.getUserName(), "test"));
        
    }

    

    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_User() {
        System.out.println("queryPictures(user)");
  
        int rand = (int )(Math.random() * 50 + 1);
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        Picture localPic1 = new Picture("Title", "Desc", "randomUrl"+rand+".com");
        Picture localPic2 = new Picture("Title", "Desc", "randomUrl"+rand*2+".com");
         
        Controller.newUser(localUser1);
        Controller.newPicture(localPic1, localUser1);
        Controller.newPicture(localPic2, localUser1);
        
        assertTrue(Controller.queryPictures(localUser1).size() ==  2);
        
        Controller.deletePicture(localPic1);
        Controller.deletePicture(localPic2);
        Controller.deleteUser(localUser1);
    }

    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_string() {
        System.out.println("queryPictures(String)");
        System.out.println("queryPictures(user)");
        
        
        int rand = (int )(Math.random() * 50 + 1);
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        Picture localPic1 = new Picture("Title", "Desc", "randomUrl"+rand+".com");
        Picture localPic2 = new Picture("Title", "Desc", "randomUrl"+rand*2+".com");
         
        Controller.newUser(localUser1);
        Controller.newPicture(localPic1, localUser1);
        Controller.newPicture(localPic2, localUser1);
        
        assertTrue(Controller.queryPictures(localUser1.getUserName()).size() ==  2);
        
        Controller.deletePicture(localPic1);
        Controller.deletePicture(localPic2);
        Controller.deleteUser(localUser1);
        
    }

    /**
     * Test of findUser method, of class Controller.
     */
    @Test
    public void testFindUser() {
        System.out.println("findUser(String)");
        int numberOfpictures = Controller.queryPictures().size();
        int rand = (int )(Math.random() * 50 + 1);
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
      
        Controller.newUser(localUser1);
        
        assertEquals(Controller.findUser(localUser1.getUserName()).getUserName(), localUser1.getUserName());
        
        Controller.deleteUser(localUser1);
        
    }

    
    
    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_0args() {
        System.out.println("queryPictures()");
        int numberOfpictures = Controller.queryPictures().size();
        int rand = (int )(Math.random() * 50 + 1);
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        Picture pic = new Picture("Title", "Desc", "randomUrl"+rand+".com");
        
        Controller.newUser(localUser1);
        Controller.newPicture(pic, localUser1);
        
        assertTrue(Controller.queryPictures().size() == numberOfpictures + 1);
        Controller.deleteUser(localUser1);
        Controller.deletePicture(pic);
    }

}
