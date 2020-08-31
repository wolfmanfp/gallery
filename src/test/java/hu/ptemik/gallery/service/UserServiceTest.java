package hu.ptemik.gallery.service;

import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.util.Encrypt;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author János
 */
public class UserServiceTest {
    
    private static SessionFactory sessionFactory;
    private static UserService userService;
    private User user1;

    @BeforeAll
    public static void setUpClass() {
        sessionFactory = HibernateUtil.getSessionFactory();
        userService = new UserService();
    }

    @BeforeEach
    public void setUp() {
        user1 = new User("loremipsum", Encrypt.encrypt("test"), "lorem@ips.um", "Lorem", "Ipsum");
        userService.newUser(user1);
    }

    @AfterEach
    public void tearDown() {
        userService.deleteUser(user1);
    }

    @AfterAll
    public static void tearDownClass() {
        sessionFactory.close();
    }

    @Test
    public void testNewUser() throws Exception {
        User user2 = new User("lorem2", "asdf","asd@a.sd", "Lórum", "Ipse");

        assertTrue(userService.newUser(user2));
        assertFalse(userService.newUser(user2));
        userService.deleteUser(user2);
    }

    @Test
    public void testQueryUsers() {
        int numberOfUsers = userService.queryUsers().size();
        User user2 = new User("lorem2", "asdf","asd@a.sd", "Lórum", "Ipse");

        userService.newUser(user2);
        assertEquals(numberOfUsers + 1, userService.queryUsers().size());
        userService.deleteUser(user2);
    }

    @Test
    public void testSubmitLogin() {
        assertNotNull(userService.submitLogin(user1.getUserName(), "test"));
    }

    @Test
    public void testFindUser() {
        assertEquals(userService.findUser(user1.getUserName()).getUserName(), "loremipsum");
    }

}
