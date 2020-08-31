package hu.ptemik.gallery.service;

import hu.ptemik.gallery.entities.Picture;
import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PictureServiceTest {
    
    private User user1;
    private static SessionFactory sessionFactory;
    private static UserService userService;
    private static PictureService pictureService;

    @BeforeAll
    public static void setUpClass() {
        sessionFactory = HibernateUtil.getSessionFactory();
        userService = new UserService();
        pictureService = new PictureService();
    }

    @BeforeEach
    public void setUp() {
        user1 = new User("loremipsum", "test", "lorem@ips.um", "Lorem", "Ipsum");
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
    public void testNewPicture() throws Exception {
        Picture pic = new Picture("Title", "Desc", "url");

        assertTrue(pictureService.newPicture(pic, user1));
        assertFalse(pictureService.newPicture(pic, user1));
        pictureService.deletePicture(pic);
    }

    @Test
    public void testQueryPictures() {
        Picture pic1 = new Picture("Title", "Desc", "url");
        Picture pic2 = new Picture("Title2", "Desc2", "url2");

        pictureService.newPicture(pic1, user1);
        pictureService.newPicture(pic2, user1);

        assertEquals(pictureService.queryPictures(user1.getUserName()).size(), 2);

        pictureService.deletePicture(pic1);
        pictureService.deletePicture(pic2);
    }

}
