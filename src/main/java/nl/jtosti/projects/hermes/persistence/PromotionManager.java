package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Promotion;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PromotionManager extends JPABase implements PromotionDAO {

    @Override
    public Promotion get(int id) {
        EntityManager entityManager = super.getConnection();
        Promotion promotion = entityManager.find(Promotion.class, id);
        entityManager.close();
        if (promotion == null) {
            throw new EntityNotFoundException();
        }
        return promotion;
    }

    @Override
    public Promotion save(Promotion promotion) {
        return super.persist(promotion);
    }

    @Override
    public Promotion update(Promotion promotion) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Promotion dbPromotion = entityManager.find(Promotion.class, promotion.getId());
        if (dbPromotion == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        dbPromotion.setName(promotion.getName());
        transaction.commit();
        entityManager.close();
        return promotion;
    }

    @Override
    public boolean delete(Promotion promotion) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Promotion dbPromotion = entityManager.find(Promotion.class, promotion.getId());
        if (dbPromotion == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        entityManager.remove(promotion);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<Promotion> getAll() {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Promotion");
        List<Promotion> promotions = query.getResultList();
        entityManager.close();
        return promotions;
    }

    @Override
    public List<Promotion> getPromotionsByLocationId(int locationId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Promotion where Promotion.type.location.id = ?")
                .setParameter(0, locationId);
        List<Promotion> promotions = query.getResultList();
        entityManager.close();
        return promotions;
    }

    @Override
    public List<Promotion> getPromotionsByLocationIdByUserId(int locationId, int userId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Promotion where Promotion.owner.id = ? and Promotion.type.location.id = ?")
                .setParameter(0, userId)
                .setParameter(1, locationId);
        List<Promotion> promotions = query.getResultList();
        entityManager.close();
        return promotions;
    }
}
