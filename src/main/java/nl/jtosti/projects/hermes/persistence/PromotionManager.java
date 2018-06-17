package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Promotion;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
        Promotion dbPromotion = this.get(promotion.getId());
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
    public boolean delete(Promotion promotion) {
        Promotion dbPromotion = this.get(promotion.getId());
        try {
            if (dbPromotion != null) {
                em.getTransaction().begin();
                em.remove(dbPromotion);
                em.getTransaction().commit();
                return true;
            }
        } catch (EntityNotFoundException e) {
            System.out.println(promotion.toString() + " not found");
        }
        return false;
    }

    @Override
    public List<Promotion> getAll() {
        return em.createQuery("SELECT p FROM Promotion p", Promotion.class).getResultList();
    }
}
