package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Screen;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
        Screen dbScreen = this.get(screen.getId());
        em.getTransaction().begin();
        dbScreen.setName(screen.getName());
        dbScreen.setHeight(screen.getHeight());
        dbScreen.setWidth(screen.getWidth());
        em.getTransaction().commit();
        return screen;
    }

    @Override
    public boolean delete(Screen screen) {
        Screen dbScreen = this.get(screen.getId());
        try {
            if (dbScreen != null) {
                em.getTransaction().begin();
                em.remove(dbScreen);
                em.getTransaction().commit();
                return true;
            }
        } catch (EntityNotFoundException e) {
            System.out.println(screen.toString() + " not found");
        }
        return false;
    }

    @Override
    public List<Screen> getAll() {
        return em.createQuery("SELECT s FROM Screen s", Screen.class).getResultList();
    }
}
