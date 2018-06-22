package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
        return this.get(location.getId());
    }

    @Override
    public Location update(Location location) {
        Location dbLocation = this.get(location.getId());
        em.getTransaction().begin();
        dbLocation.setCity(location.getCity());
        dbLocation.setCountry(location.getCountry());
        dbLocation.setHouseNumber(location.getHouseNumber());
        dbLocation.setName(location.getName());
        dbLocation.setStreet(location.getStreet());
        dbLocation.setZipCode(location.getZipCode());
        em.getTransaction().commit();
        em.refresh(dbLocation);
        return this.get(location.getId());
    }

    @Override
    public boolean delete(Location location) {
        Location dbLocation = this.get(location.getId());
        try {
            if (dbLocation != null) {
                em.getTransaction().begin();
                em.remove(dbLocation);
                em.getTransaction().commit();
                return true;
            }
        } catch (EntityNotFoundException e) {
            System.out.println(location.toString() + " not found");
        }
        return false;
    }

    @Override
    public List<Location> getAll() {
        return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
    }
}
