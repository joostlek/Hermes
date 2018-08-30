package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class TypeManager extends JPABase implements TypeDAO {

    @Override
    public Type get(int id) {
        EntityManager entityManager = super.getConnection();
        Type type = entityManager.find(Type.class, id);
        entityManager.close();
        if (type == null) {
            throw new EntityNotFoundException();
        }
        return type;
    }

    @Override
    public Type save(Type type) {
        return super.persist(type);
    }

    @Override
    public Type update(Type type) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Type dbType = entityManager.find(Type.class, type.getId());
        if (dbType == null) {
            entityManager.close();
            throw new EntityNotFoundException();
        }
        dbType.setActive(type.isActive());
        dbType.setAmountOfImages(type.getAmountOfImages());
        dbType.setExclusive(type.isExclusive());
        dbType.setName(type.getName());
        dbType.setPrice(type.getPrice());
        dbType.setTime(type.getTime());
        dbType.setLocation(type.getLocation());
        transaction.commit();
        entityManager.close();
        return type;
    }

    @Override
    public boolean delete(Type type) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Type dbType = entityManager.find(Type.class, type.getId());
        if (dbType == null) {
            entityManager.close();
            throw new EntityNotFoundException();
        }
        entityManager.remove(dbType);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<Type> getAllByLocationId(int locationId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("from Type where Type.location.id = ?")
                .setParameter(0, locationId);
        List<Type> types = query.getResultList();
        entityManager.close();
        return types;
    }


}
