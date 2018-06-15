package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Promotion;

import java.util.List;

public interface PromotionDAO {
    Promotion get(int id);
    Promotion save(Promotion promotion);
    Promotion update(Promotion promotion);
    void delete(Promotion promotion);
    List<Promotion> getAll();
}
