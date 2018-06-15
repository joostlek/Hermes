package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Promotion;

import javax.persistence.EntityManager;
import java.util.List;

public class PromotionManager extends JPABase implements PromotionDAO {
    private EntityManager em = super.getConnection();
    @Override
    public Promotion get(int id) {
        return em.find(Promotion.class, id);
    }

    @Override
    public Promotion save(Promotion promotion) {
        em.getTransaction().begin();
        em.persist(promotion);
        em.getTransaction().commit();
        return promotion;
    }

    @Override
    public Promotion update(Promotion promotion) {
        Promotion dbPromotion = em.find(Promotion.class, promotion.getId());
        em.getTransaction().begin();
        dbPromotion.setName(promotion.getName());
        dbPromotion.setStartDate(promotion.getStartDate());
        dbPromotion.setOwner(promotion.getOwner());
        dbPromotion.setType(promotion.getType());
        dbPromotion.setImages(promotion.getImages());
        em.getTransaction().commit();
        return promotion;
    }

    @Override
    public void delete(Promotion promotion) {
        em.getTransaction().begin();
        em.remove(promotion);
        em.getTransaction().commit();
    }

    @Override
    public List<Promotion> getAll() {
        return em.createQuery("SELECT p FROM Promotion p", Promotion.class).getResultList();
    }
}
