package nl.jtosti.projects.hermes.persistence;


import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.util.Util;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserManager extends JPABase implements UserDAO {

    @Override
    public User get(int id) {
        EntityManager entityManager = super.getConnection();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    @Override
    public User save(User user) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
        return user;
    }

    @Override
    public User update(User user) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User dbUser = entityManager.find(User.class, user.getId());
        if (dbUser == null) {
            entityManager.close();
            return null;
        }
        dbUser.setCity(user.getCity());
        transaction.commit();
        entityManager.close();
        return user;
    }

    @Override
    public boolean delete(User user) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User dbUser = entityManager.find(User.class, user.getId());
        if (dbUser == null) {
            entityManager.close();
            throw new EntityNotFoundException();
        }
        entityManager.remove(dbUser);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<User> getAll() {
//        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        return null;
    }

    @Override
    public List<User> getAllByLocationId(int locationId) {
        return null;
    }

    @Override
    public User getUserByLogin(String email, String password) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        Root<User> userRoot = query.from(User.class);
//        query.select(userRoot);
//        query.where(criteriaBuilder.equal(userRoot.get("email"), email),
//                criteriaBuilder.equal(userRoot.get("password"), Util.MD5(password)));
//        TypedQuery<User> userQuery = em.createQuery(query);
//        try {
//            return userQuery.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
        return null;
    }
}
