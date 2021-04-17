package aprohirdetes;

import Model.Hirdetesek;
import java.util.List;

public interface HirdetesekDAO extends AutoCloseable
{
    public void saveHirdetes(Hirdetesek hirdetes);
    
    public void deleteHirdetes(Hirdetesek hirdetes);
    
    public void updateHirdetes(Hirdetesek hirdetes);
    
    public List<Hirdetesek> getHirdetesek();
    
}
