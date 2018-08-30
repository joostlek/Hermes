package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class LocationManager extends JPABase implements LocationDAO {

    @Override
    public Location get(int id) {
        EntityManager entityManager = super.getConnection();
        Location location = entityManager.find(Location.class, id);
        entityManager.close();
        if (location == null) {
            throw new EntityNotFoundException();
        }
        return location;
    }

    @Override
    public Location save(Location location) {
        return super.persist(location);
    }

    @Override
    public Location update(Location location) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Location dbLocation = entityManager.find(Location.class, location.getId());
        if (dbLocation == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        dbLocation.setName(location.getName());
        dbLocation.setStreet(location.getStreet());
        dbLocation.setHouseNumber(location.getHouseNumber());
        dbLocation.setZipCode(location.getZipCode());
        dbLocation.setCity(location.getCity());
        dbLocation.setCountry(location.getCountry());
        transaction.commit();
        entityManager.close();
        return location;
    }

    @Override
    public boolean delete(Location location) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Location dbLocation = entityManager.find(Location.class, location.getId());
        if (dbLocation == null) {
            transaction.rollback();
            entityManager.close();
            throw new EntityNotFoundException();
        }
        entityManager.remove(dbLocation);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<Location> getAll() {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Location ");
        List<Location> locations = query.getResultList();
        entityManager.close();
        return locations;
    }

    @Override
    public List<Location> getLocationsByUserId(int userId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("select UserLocation.location from UserLocation where UserLocation.user.id = ?")
                .setParameter(0, userId);
        List<Location> locations = query.getResultList();
        entityManager.close();
        return locations;
    }
}
