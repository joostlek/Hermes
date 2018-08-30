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
        if (user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @Override
    public User save(User user) {
        return super.persist(user);
    }

    @Override
    public User update(User user) {
        EntityManager entityManager = super.getConnection();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User dbUser = entityManager.find(User.class, user.getId());
        if (dbUser == null) {
            entityManager.close();
            throw new EntityNotFoundException();
        }
        dbUser.setFirstName(user.getFirstName());
        dbUser.setMiddleName(!user.getMiddleName().equals("") ? user.getMiddleName() : null);
        dbUser.setLastName(user.getLastName());
        dbUser.setStreet(user.getStreet());
        dbUser.setHouseNumber(user.getHouseNumber());
        dbUser.setZipCode(user.getZipCode());
        dbUser.setCity(user.getCity());
        dbUser.setCountry(user.getCountry());
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
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("select u from User u", User.class);
        List<User> users = query.getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public List<User> getAllByLocationId(int locationId) {
        EntityManager entityManager = super.getConnection();
        Query query = entityManager.createQuery("select UserLocation.user from UserLocation where UserLocation.location.id = ?")
                .setParameter(0, locationId);
        List<User> users = query.getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public User getUserByLogin(String email, String password) {
        EntityManager entityManager = super.getConnection();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.select(userRoot);
        query.where(criteriaBuilder.equal(userRoot.get("email"), email),
                criteriaBuilder.equal(userRoot.get("password"), Util.toDatabase(password)));
        TypedQuery<User> userQuery = entityManager.createQuery(query);
        try {
            User user = userQuery.getSingleResult();
            entityManager.close();
            return user;
        } catch (NoResultException e) {
            entityManager.close();
            return null;
        }
    }
}
