package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
        em.clear();
        return this.get(type.getId());
    }

    @Override
    public Type update(Type type) {
        Type dbType = this.get(type.getId());
        em.getTransaction().begin();
        dbType.setActive(type.isActive());
        dbType.setAmountOfImages(type.getAmountOfImages());
        dbType.setExclusive(type.isExclusive());
        dbType.setName(type.getName());
        dbType.setPrice(type.getPrice());
        dbType.setTime(type.getTime());
        dbType.setLocation(type.getLocation());
        em.getTransaction().commit();
        em.clear();
        return this.get(type.getId());
    }

    @Override
    public boolean delete(Type type) {
        Type dbType = this.get(type.getId());
        try {
            if (dbType != null) {
                em.getTransaction().begin();
                em.remove(dbType);
                em.getTransaction().commit();
                em.clear();
                return true;
            }
        } catch (EntityNotFoundException e) {
            System.out.println(type.toString() + " not found");
        }
        return false;
    }

    @Override
    public List<Type> getAll() {
        return em.createQuery("SELECT t FROM Type t", Type.class).getResultList();
    }
}
