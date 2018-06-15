package nl.jtosti.projects.hermes;


import nl.jtosti.projects.hermes.models.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserManager extends JPABase implements UserDAO {
    private EntityManager em = super.getConnection();
    @Override
    public User save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    @Override
    public User get(int id) {
        return em.getReference(User.class, id);
    }

    @Override
    public User update(User user) {
        User db_user = em.find(User.class, user.getId());
        em.getTransaction().begin();
        db_user.setFirstName(user.getFirstName());
        db_user.setMiddleName(user.getMiddleName());
        db_user.setLastName(user.getLastName());
        db_user.setEmail(user.getEmail());
        db_user.setPhoneNumber(user.getPhoneNumber());
        db_user.setStreet(user.getStreet());
        db_user.setHouseNumber(user.getHouseNumber());
        db_user.setZipCode(user.getZipCode());
        db_user.setCity(user.getCity());
        db_user.setCountry(user.getCountry());
        em.getTransaction().commit();
        return user;
    }

    @Override
    public void delete(User user) {
        em.getTransaction().begin();
        em.remove(em.find(User.class, user.getId()));
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
