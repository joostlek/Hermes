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
        Location location_db = em.find(Location.class, location.getId());
        location_db.setCity(location.getCity());
        location_db.setCountry(location.getCountry());
        location_db.setHouseNumber(location.getHouseNumber());
        location_db.setName(location.getName());
        location_db.setStreet(location.getStreet());
        location_db.setZipCode(location.getZipCode());
        location_db.setTypes(location.getTypes());
        location_db.setOwner(location.getOwner());
        location_db.setScreens(location.getScreens());
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
