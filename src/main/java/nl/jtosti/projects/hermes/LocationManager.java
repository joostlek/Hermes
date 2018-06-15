package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Location;

import javax.persistence.EntityManager;
import java.util.List;

public class LocationManager extends JPABase implements LocationDAO {
    private EntityManager em = super.getConnection();
    @Override
    public Location get(int id) {
        return em.find(Location.class, id);
    }

    @Override
    public Location save(Location location) {
        em.getTransaction().begin();
        em.persist(location);
        em.getTransaction().commit();
        return location;
    }

    @Override
    public Location update(Location location) {
        Location dbLocation = em.find(Location.class, location.getId());
        em.getTransaction().begin();
        dbLocation.setCity(location.getCity());
        dbLocation.setCountry(location.getCountry());
        dbLocation.setHouseNumber(location.getHouseNumber());
        dbLocation.setName(location.getName());
        dbLocation.setStreet(location.getStreet());
        dbLocation.setZipCode(location.getZipCode());
        dbLocation.setTypes(location.getTypes());
        dbLocation.setOwner(location.getOwner());
        dbLocation.setScreens(location.getScreens());
        em.getTransaction().commit();
        return location;
    }

    @Override
    public void delete(Location location) {
        em.getTransaction().begin();
        em.remove(location);
        em.getTransaction().commit();
    }

    @Override
    public List<Location> getAll() {
        return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
    }
}
