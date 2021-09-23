package aa.mydatas.jpa.learnSpring;


import aa.mydatas.jpa.entity.Goods;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class OnlyHibernate {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // 使用AOP
        try {
            ArrayList<Goods> goodsList = new ArrayList<>();
            transaction.begin();
            for (int i = 0; i < 1000; i++) {
                Goods goods = new Goods();
                goods.setDesc("这是一本书" + i);
                goods.setName("这是书名" + i);
                goodsList.add(goods);
            }


        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {
            transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
