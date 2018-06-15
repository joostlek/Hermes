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
        User dbUser = em.find(User.class, user.getId());
        em.getTransaction().begin();
        dbUser.setFirstName(user.getFirstName());
        dbUser.setMiddleName(user.getMiddleName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhoneNumber(user.getPhoneNumber());
        dbUser.setStreet(user.getStreet());
        dbUser.setHouseNumber(user.getHouseNumber());
        dbUser.setZipCode(user.getZipCode());
        dbUser.setCity(user.getCity());
        dbUser.setCountry(user.getCountry());
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
