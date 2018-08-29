package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ImageManager extends JPABase implements ImageDAO {

    @Override
    public Image get(int id) {
        EntityManager entityManager = super.getConnection();
        Image image = entityManager.find(Image.class, id);
        entityManager.close();
        return image;
    }

    @Override
    public Image save(Image image) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(image);
        transaction.commit();
        entityManager.close();
        return image;
    }

    @Override
    public Image update(Image image) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Image dbImage = entityManager.find(Image.class, image.getId());
        if (dbImage == null) {
            entityManager.close();
            return null;
        }
        dbImage.setName(image.getName());
        dbImage.setTime(image.getTime());
        transaction.commit();
        entityManager.close();
        return image;
    }

    @Override
    public boolean delete(Image image) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Image dbImage = entityManager.find(Image.class, image.getId());
        if (dbImage == null) {
            entityManager.close();
            throw new EntityNotFoundException();
        }
        entityManager.remove(dbImage);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<Image> getAllByLocationId(int locationId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Image as image where image.promotion = Promotion and Promotion.type = Type and Type.location.id = ?", List.class)
                .setParameter(0, locationId);
        List<Image> images = query.getResultList();
        entityManager.close();
        return images;
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
        return this.get(image.getId());
    }
}
