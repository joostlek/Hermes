package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Screen;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ScreenManager extends JPABase implements ScreenDAO {

    @Override
    public Screen get(int id) {
        EntityManager entityManager = super.getConnection();
        Screen screen = entityManager.find(Screen.class, id);
        entityManager.close();
        if (screen == null) {
            throw new EntityNotFoundException();
        }
        return screen;
    }

    @Override
    public Screen save(Screen screen) {
        return super.persist(screen);
    }

    @Override
    public Screen update(Screen screen) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Screen dbScreen = entityManager.find(Screen.class, screen.getId());
        if (dbScreen == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        dbScreen.setName(screen.getName());
        dbScreen.setHeight(screen.getHeight());
        dbScreen.setWidth(screen.getWidth());
        transaction.commit();
        entityManager.close();
        return screen;
    }

    @Override
    public boolean delete(Screen screen) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Screen dbScreen = entityManager.find(Screen.class, screen.getId());
        if (dbScreen == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        entityManager.remove(dbScreen);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<Screen> getAllByLocationId(int locationId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Screen where location.id = ?")
                .setParameter(0, locationId);
        List<Screen> screens = query.getResultList();
        entityManager.close();
        return screens;
    }

    @Override
    public List<Screen> getAll() {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Screen");
        List<Screen> screens = query.getResultList();
        entityManager.close();
        return screens;
    }
}
