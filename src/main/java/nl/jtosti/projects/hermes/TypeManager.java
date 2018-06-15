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
        Type type_db = em.find(Type.class, type.getId());
        type_db.setActive(type.isActive());
        type_db.setAmountOfImages(type.getAmountOfImages());
        type_db.setExclusive(type.isExclusive());
        type_db.setName(type.getName());
        type_db.setPrice(type.getPrice());
        type_db.setTime(type.getTime());
        type_db.setLocation(type.getLocation());
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
