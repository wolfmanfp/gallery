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
        sessionFactory.close();
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
        pic1.setUser(user1);
        pic1.setUrl("www.doge.com/swagie");

        pic2.setTitle("Cool'n'The Gang2");
        pic2.setDescription("Such picture2");
        pic2.setUser(user1);
        pic2.setUrl("www.doge.com/swagie2");
        
        pic2.setTitle("#1 Dad");
        pic2.setDescription("#GOT,#Fire,#LordOfLight");
        pic2.setUser(user2);
        pic2.setUrl("http://img-9gag-fun.9cache.com/photo/aPGwYQg_460s.jpg");
    }

    @After
    public void tearDown() {
        session.close();
    }

    /**
     * Test of queryUsers method, of class Controller.
     */
    /**
     * Test of newUser method, of class Controller.
     */
    @Test
    public void testNewUser() throws Exception {
        int numberOfUsers = Controller.queryUsers().size();
        
        System.out.println("newUser");

        Controller.newUser(user1);
        Controller.newUser(user2);

        assertTrue(Controller.queryUsers().size()==numberOfUsers + 2);
    }

    /**
     * Test of newPicture method, of class Controller.
     */
    @Test
    public void testNewPicture() throws Exception {
        System.out.println("newPicture");
        int numberOfPictures = Controller.queryPictures().size();
        
        Controller.newPicture(pic1, user1);
        Controller.newPicture(pic2, user1);
        Controller.newPicture(pic3, user2);

        assertTrue(Controller.queryPictures().size() == numberOfPictures+3);
    }
    
    @Test
    public void testQueryUsers() {
        System.out.println("queryUsers");
        List<User> expResult = null;
        List<User> result = Controller.queryUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of submitLogin method, of class Controller.
     */
    @Test
    public void testSubmitLogin() {
        System.out.println("submitLogin");
        String userName = "";
        String pw = "";
        User expResult = null;
        User result = Controller.submitLogin(userName, pw);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_User() {
        System.out.println("queryPictures");
        User user = null;
        List<Picture> expResult = null;
        List<Picture> result = Controller.queryPictures(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_int() {
        System.out.println("queryPictures");
        int userId = 0;
        List<Picture> expResult = null;
        List<Picture> result = Controller.queryPictures(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUser method, of class Controller.
     */
    @Test
    public void testFindUser() {
        System.out.println("findUser");
        int userId = 0;
        User expResult = null;
        User result = Controller.findUser(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_0args() {
        System.out.println("queryPictures");
        List<Picture> expResult = null;
        List<Picture> result = Controller.queryPictures();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
