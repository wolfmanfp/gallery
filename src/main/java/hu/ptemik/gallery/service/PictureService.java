package hu.ptemik.gallery.service;

import com.querydsl.jpa.hibernate.HibernateQuery;
import hu.ptemik.gallery.entities.Picture;
import hu.ptemik.gallery.entities.QPicture;
import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PictureService {
    /**
     * Registers a new Picture record.
     *
     * @param pic The url of the picture.
     * @param user The uploader.
     * @return True if successful, false if not.
     */
    public boolean newPicture(Picture pic, User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            if (user != null && pic != null) {
                user.addPicture(pic);
                pic.setUser(user);
                session.beginTransaction();
                session.persist(pic);
                session.getTransaction().commit();
                session.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            if (session.isOpen()) {
                session.close();
            }
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Returns a list of Picture objects according to the userName.
     *
     * @param userName
     * @return List<User>
     */
    public List<Picture> queryPictures(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QPicture picture = QPicture.picture;
        List<Picture> pictures = new HibernateQuery<>(session).select(picture)
                .from(picture)
                .where(picture.user.userName.eq(userName))
                .fetch();

        session.close();
        return pictures;
    }

    /**
     * Finds a picture by its ID.
     *
     * @param id
     * @return Picture
     */
    public Picture findPicture(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QPicture qPicture = QPicture.picture;
        Picture picture = new HibernateQuery<>(session).select(qPicture)
                .from(qPicture)
                .where(qPicture.id.eq(id))
                .fetchOne();

        session.close();
        return picture;
    }

    /**
     * Deletes a Picture according to Picture object
     *
     * @param pic Picture
     * @return Returns true if successful, false if not.
     */
    public boolean deletePicture(Picture pic) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.delete(pic);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception ex) {
            if (session.isOpen()) {
                session.close();
            }
            return false;
        }
    }
}
