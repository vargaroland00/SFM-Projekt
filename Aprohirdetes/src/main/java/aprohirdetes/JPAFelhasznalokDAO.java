package aprohirdetes;

import Model.Felhasznalok;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JPAFelhasznalokDAO implements FelhasznalokDAO
{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hirdetesek.pu"); // a perzisztencia egység neve
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @Override
    public void saveFelhasznalo(Felhasznalok felhasznalo) {
        entityManager.getTransaction().begin();
        entityManager.persist(felhasznalo);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteFelhasznalo(Felhasznalok felhasznalo) {
        entityManager.getTransaction().begin();
        entityManager.remove(felhasznalo);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateFelhasznalo(Felhasznalok felhasznalo) {
        saveFelhasznalo(felhasznalo);
    }

    @Override
    public List<Felhasznalok> getFelhasznalok() {
        TypedQuery<Felhasznalok> query = entityManager.createQuery("SELECT felhasznalo FROM Felhasznalo felhasznalo", Felhasznalok.class);
        List<Felhasznalok> felhasznalok = query.getResultList();
        
        return felhasznalok;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
    
}
