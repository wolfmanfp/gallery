package hu.ptemik.gallery.control;

import hu.ptemik.gallery.entities.Picture;
import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.util.Encrypt;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author János
 */
public class ControllerTest {

    private static SessionFactory sessionFactory;
    private User user1 = new User();
    private User user2 = new User();
    private Picture pic1 = new Picture();
    private Picture pic2 = new Picture();
    private Picture pic3 = new Picture();

    public ControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterAll
    public static void tearDownClass() {
//        sessionFactory.close();
    }

    @BeforeEach
    public void setUp() {
        user1.setFirstName("Péter");
        user1.setLastName("Farkas");
        user1.setPasswordHash("1234");
        user1.setUserName("wolfmanfp");
        user1.setEmail("farkas.peter1995@gmail.com");

        user2.setFirstName("János");
        user2.setLastName("Farkas");
        user2.setPasswordHash("doge");
        user2.setUserName("jancsiij");
        user2.setEmail("jancsiij@gmail.com");
        
        pic1.setTitle("Cool'n'The Gang");
        pic1.setDescription("#Summer,#rekt");
        pic1.setUrl("http://www.fluidraconnect.com/wp-content/uploads/2014/02/foto-nova.jpg");

        pic2.setTitle("Spoderman");
        pic2.setDescription("discripzun");
        pic2.setUrl("https://pbs.twimg.com/profile_images/2306531900/sec5qf2agnxnlnxcjtjk_400x400.png");
        
        pic3.setTitle("#1 Dad");
        pic3.setDescription("#GOT,#Fire,#LordOfLight");
        pic3.setUrl("http://img-9gag-fun.9cache.com/photo/aPGwYQg_460s.jpg");
        
        Controller.newUser(user1);
        Controller.newUser(user2);
        Controller.newPicture(pic1, user1);
        Controller.newPicture(pic2, user1);
        Controller.newPicture(pic3, user2);
    }

    @AfterEach
    public void tearDown() {
        //session.close();
    }

    /**
     * Test of newUser method, of class Controller.
     */
    @Test
    public void testNewUser() throws Exception {
        int rand = (int) (Math.random() * 500 + 1);
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
        int rand = (int) (Math.random() * 500 + 1);
        System.out.println("newPicture");
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        Picture pic = new Picture("Title", "Desc", "randomUrl"+rand+".com");
        
        Controller.newUser(localUser1);
   
        assertTrue(Controller.newPicture(pic, localUser1));
        assertFalse(Controller.newPicture(pic, localUser1));
        Controller.deletePicture(pic);
        Thread.sleep(1000);
        Controller.deleteUser(localUser1);
    }
    
    @Test
    public void testQueryUsers() {
        int rand = (int) (Math.random() * 500 + 1);
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
        
        int rand = (int) (Math.random() * 500 + 1);
        User localUser1 = new User("Test"+rand, Encrypt.encrypt("test"),"test"+rand+"@test.t", "Test", "Tamás");
        Controller.newUser(localUser1);
        
        assertNotNull(Controller.submitLogin(localUser1.getUserName(), "test"));
        Controller.deleteUser(localUser1);
    }

    /**
     * Test of queryPictures method, of class Controller.
     */
    @Test
    public void testQueryPictures_string() {
        System.out.println("queryPictures(String)");
        
        int rand = (int )(Math.random() * 500 + 1);
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
        Picture localPic1 = new Picture("Title", "Desc", "randomUrl"+rand+".com");
        Picture localPic2 = new Picture("Title", "Desc", "randomUrl"+rand*2+".com");
         
        Controller.newUser(localUser1);
        Controller.newPicture(localPic1, localUser1);
        Controller.newPicture(localPic2, localUser1);
        
        assertTrue(Controller.queryPictures(localUser1.getUserName()).size() ==  2);
        
        Controller.deletePicture(localPic1);
        Controller.deletePicture(localPic2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Controller.deleteUser(localUser1);
    }

    /**
     * Test of findUser method, of class Controller.
     */
    @Test
    public void testFindUser() {
        System.out.println("findUser(String)");
        int rand = (int) (Math.random() * 500 + 1);
        User localUser1 = new User("Test"+rand, "test"+rand,"test"+rand+"@test.t", "Test", "Tamás");
      
        Controller.newUser(localUser1);
        
        assertEquals(Controller.findUser(localUser1.getUserName()).getUserName(), localUser1.getUserName());
        
        Controller.deleteUser(localUser1);
    }

}
