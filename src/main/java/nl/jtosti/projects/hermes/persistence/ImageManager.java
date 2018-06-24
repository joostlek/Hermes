package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImageManager extends JPABase implements ImageDAO {
    private EntityManager em = super.getConnection();
    @Override
    public Image get(int id) {
        return em.find(Image.class, id);
    }

    @Override
    public Image save(Image image) {
        em.getTransaction().begin();
        em.persist(image);
        em.getTransaction().commit();
        em.clear();
        return this.get(image.getId());
    }

    @Override
    public Image update(Image image) {
        em.getTransaction().begin();
        Image dbImage = this.get(image.getId());
        dbImage.setName(image.getName());
        dbImage.setTime(image.getTime());
        em.getTransaction().commit();
        em.clear();
        return this.get(image.getId());
    }

    @Override
    public boolean delete(Image image) {
        Image dbImage = this.get(image.getId());
        try {
            if (dbImage != null) {
                em.getTransaction().begin();
                em.remove(dbImage);
                em.getTransaction().commit();
                em.clear();
                return true;
            }
        } catch (EntityNotFoundException e) {
            System.out.println(image.toString() + " not found");
        }
        return false;
    }

    @Override
    public List<Image> getAll() {
        return em.createQuery("SELECT i FROM Image i", Image.class).getResultList();
    }

    @Override
    public List<Image> getUncheckedImages(User user) {
//        return (List<Image>) em.createQuery("from Image as image where image.promotion = Promotion and Promotion.type = Type and Type.location = Location and Location.owner = ?")
//                .setParameter(0, user)
//                .getResultList();
        return new ArrayList<>();
    }

    @Override
    public Image updateActive(Image image) {
        Image dbImage = this.get(image.getId());
        em.getTransaction().begin();
        dbImage.setActive(image.isActive());
        em.getTransaction().commit();
        em.refresh(dbImage);
        return this.get(image.getId());
    }
}
