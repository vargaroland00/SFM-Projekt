package aprohirdetes;

import Model.Felhasznalok;
import java.util.List;

public interface FelhasznalokDAO extends AutoCloseable
{
    public void saveFelhasznalo(Felhasznalok felhasznalo);
    
    public void deleteFelhasznalo(Felhasznalok felhasznalo);
    
    public void updateFelhasznalo(Felhasznalok felhasznalo);
    
    public List<Felhasznalok> getFelhasznalo();
}
