package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Promotion;

import java.util.List;

public interface PromotionDAO {
    Promotion get(int id);
    Promotion save(Promotion promotion);
    Promotion update(Promotion promotion);
    boolean delete(Promotion promotion);
    List<Promotion> getAll();
    List<Promotion> getPromotionsByLocationId(int locationId);
    List<Promotion> getPromotionsByLocationIdByUserId(int locationId, int userId);
}
