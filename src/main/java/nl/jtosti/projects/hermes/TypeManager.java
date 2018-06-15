package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Type;

import javax.persistence.EntityManager;
import java.util.List;

public class TypeManager extends JPABase implements TypeDAO {
    private EntityManager em = super.getConnection();
    @Override
    public Type get(int id) {
        return em.find(Type.class, id);
    }

    @Override
    public Type save(Type type) {
        em.getTransaction().begin();
        em.persist(type);
        em.getTransaction().commit();
        return type;
    }

    @Override
    public Type update(Type type) {
        Type dbType = em.find(Type.class, type.getId());
        em.getTransaction().begin();
        dbType.setActive(type.isActive());
        dbType.setAmountOfImages(type.getAmountOfImages());
        dbType.setExclusive(type.isExclusive());
        dbType.setName(type.getName());
        dbType.setPrice(type.getPrice());
        dbType.setTime(type.getTime());
        dbType.setLocation(type.getLocation());
        em.getTransaction().commit();
        return type;
    }

    @Override
    public void delete(Type type) {
        em.getTransaction().begin();
        em.remove(type);
        em.getTransaction().commit();
    }

    @Override
    public List<Type> getAll() {
        return em.createQuery("SELECT t FROM Type t", Type.class).getResultList();
    }
}
