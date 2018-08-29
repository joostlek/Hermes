package nl.jtosti.projects.hermes.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPABase {
    protected final EntityManager getConnection() {
        EntityManager em;
        String cfg = "postgresql";
        EntityManagerFactory entityManagerFactory;
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<>();
        for (String envName : env.keySet()) {
            if (envName.contains("DB_USER")) {
                configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
            } else if (envName.contains("DB_PASSWORD")) {
                configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
            } else if (envName.contains("DB_URL")) {
                configOverrides.put("javax.persistence.jdbc.url", env.get(envName));
            } else if (envName.contains("DB_DRIVER")) {
                configOverrides.put("javax.persistence.jdbc.driver", env.get(envName));
            } else if (envName.contains("DB_DIALECT")) {
                configOverrides.put("hibernate.dialect", env.get(envName));
            } else if (envName.contains("DB_SHOW_SQL")) {
                configOverrides.put("hibernate.show_sql", env.get(envName));
            } else if (envName.contains("DB_AUTO_DDL")) {
                configOverrides.put("hibernate.hbm2ddl.auto", env.get(envName));
            }
        }
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(cfg, configOverrides);
            em = entityManagerFactory.createEntityManager();
            em.setFlushMode(FlushModeType.AUTO);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }
}
