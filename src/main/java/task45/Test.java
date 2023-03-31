package task45;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {

    @Autowired
    private DataInitializer dataInitializer;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // create and persist entities here...

            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        finally {
            em.close();
            emf.close();
        }
    }

}
