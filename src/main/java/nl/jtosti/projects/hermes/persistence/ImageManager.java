package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Image;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ImageManager extends JPABase implements ImageDAO {

    @Override
    public Image get(int id) {
        EntityManager entityManager = super.getConnection();
        Image image = entityManager.find(Image.class, id);
        entityManager.close();
        if (image == null) {
            throw new EntityNotFoundException();
        }
        return image;
    }

    @Override
    public Image save(Image image) {
        return super.persist(image);
    }

    @Override
    public Image update(Image image) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Image dbImage = entityManager.find(Image.class, image.getId());
        if (dbImage == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
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
            transaction.rollback();
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
    public List<Image> getUncheckedImagesByLocationId(int locationId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Image where Image.promotion.type.location = ?")
                .setParameter(0, locationId);
        List<Image> images = query.getResultList();
        entityManager.close();
        return images;
    }

    @Override
    public Image updateActive(Image image) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Image dbImage = entityManager.find(Image.class, image.getId());
        if (dbImage == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        dbImage.setActive(true);
        transaction.commit();
        entityManager.close();
        return image;
    }

    @Override
    public List<Image> getActiveByScreenId(int screenId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Image where Image.screen.id = ?")
                .setParameter(0, screenId);
        List<Image> images = query.getResultList();
        entityManager.close();
        return images;
    }
}
