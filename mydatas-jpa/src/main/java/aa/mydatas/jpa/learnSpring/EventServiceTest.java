package aa.mydatas.jpa.learnSpring;


import aa.mydatas.jpa.entity.Users;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.*;


import javax.persistence.EntityManager;

public class EventServiceTest {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        StandardServiceRegistryBuilder configure = standardServiceRegistryBuilder.configure();
        StandardServiceRegistry registry = configure.build();
        MetadataSources metadataSources = new MetadataSources(registry);
        metadataSources.addAnnotatedClass(Users.class);
        metadataSources.addAnnotatedClassName("aa.slktop.curd.jpa.entity.Account");
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        metadataBuilder.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE);
        Metadata metadata = metadataBuilder.build();
        sessionFactory = metadata.buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Before
    public void before() {
        entityManager.getTransaction().begin();
    }

    @After
    public void after() {
        entityManager.getTransaction().commit();
    }


    @Test
    public void findAll() {
        Users users = entityManager.find(Users.class, 5);
        System.out.println(users.getUserName());
    }

    @Test
    public void findById() {
    }
}