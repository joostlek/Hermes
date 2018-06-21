package nl.jtosti.projects.hermes.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class JPABase {
    protected final EntityManager getConnection() {
        EntityManager em;
        String cfg = "postgresql";
        EntityManagerFactory entityManagerFactory;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(cfg);
            em = entityManagerFactory.createEntityManager();
            em.setFlushMode(FlushModeType.AUTO);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }
}
