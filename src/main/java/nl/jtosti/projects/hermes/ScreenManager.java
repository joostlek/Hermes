package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Screen;

import javax.persistence.EntityManager;
import java.util.List;

public class ScreenManager extends JPABase implements ScreenDAO {
    private EntityManager em = super.getConnection();
    @Override
    public Screen get(int id) {
        return em.find(Screen.class, id);
    }

    @Override
    public Screen save(Screen screen) {
        em.getTransaction().begin();
        em.persist(screen);
        em.getTransaction().commit();
        return screen;
    }

    @Override
    public Screen update(Screen screen) {
        Screen dbScreen = em.find(Screen.class, screen.getId());
        em.getTransaction().begin();
        dbScreen.setAllowAds(screen.isAllowAds());
        dbScreen.setHeight(screen.getHeight());
        dbScreen.setWidth(screen.getWidth());
        dbScreen.setLocation(screen.getLocation());
        dbScreen.setImages(screen.getImages());
        em.getTransaction().commit();
        return screen;
    }

    @Override
    public void delete(Screen screen) {
        em.getTransaction().begin();
        em.remove(screen);
        em.getTransaction().commit();
    }

    @Override
    public List<Screen> getAll() {
        return em.createQuery("SELECT s FROM Screen s", Screen.class).getResultList();
    }
}
