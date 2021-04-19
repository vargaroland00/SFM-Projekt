package aprohirdetes;

import Model.Hirdetesek;
import java.util.List;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JPAHirdetesekDAO implements HirdetesekDAO
{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hirdetesek.pu"); // a perzisztencia egység neve
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @Override
    public void saveHirdetes(Hirdetesek hirdetes) {
        entityManager.getTransaction().begin();
        entityManager.persist(hirdetes);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteHirdetes(Hirdetesek hirdetes) {
        entityManager.getTransaction().begin();
        entityManager.remove(hirdetes);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateHirdetes(Hirdetesek hirdetes) {
        saveHirdetes(hirdetes);
    }

    @Override
    public List<Hirdetesek> getHirdetesek() {
        TypedQuery<Hirdetesek> query = entityManager.createQuery("SELECT hirdetes FROM Hirdetesek hirdetes", Hirdetesek.class);
        List<Hirdetesek> hirdetesek = query.getResultList();
        
        return hirdetesek;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

}
