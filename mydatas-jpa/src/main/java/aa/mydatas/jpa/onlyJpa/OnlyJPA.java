package aa.mydatas.jpa.onlyJpa;


import aa.mydatas.jpa.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OnlyJPA {

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;
    static EntityTransaction transaction;

    public static void main(String[] args) {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("mysqlPersistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Users users = entityManager.find(Users.class, 1);
            System.out.println(users);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    public static void close() {
        if (transaction != null) transaction.commit();
        if (entityManager != null) entityManager.close();
        if (entityManagerFactory != null) entityManagerFactory.close();
    }
}
