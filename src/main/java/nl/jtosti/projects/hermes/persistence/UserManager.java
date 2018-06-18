package nl.jtosti.projects.hermes.persistence;


import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        User dbUser = this.get(user.getId());
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
    public boolean delete(User user) {
        User dbUser = this.get(user.getId());
        try {
            if (dbUser.getId() != 0) {
                em.getTransaction().begin();
                em.remove(dbUser);
                em.getTransaction().commit();
                return true;
            }
        } catch (EntityNotFoundException e) {
            System.out.println(user.toString() + " not found");
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserByLogin(String email, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.select(userRoot);
        query.where(criteriaBuilder.equal(userRoot.get("email"), email),
                criteriaBuilder.equal(userRoot.get("password"), Util.MD5(password)));
        TypedQuery<User> userQuery = em.createQuery(query);
        return userQuery.getSingleResult();
    }
}
