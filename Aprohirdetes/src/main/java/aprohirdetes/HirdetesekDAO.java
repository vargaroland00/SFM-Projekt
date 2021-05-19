package aprohirdetes;

import Model.Hirdetesek;
import java.util.List;
import javafx.collections.ObservableList;

public interface HirdetesekDAO extends AutoCloseable
{
    public void saveHirdetes(Hirdetesek hirdetes);
    
    public void deleteHirdetes(Hirdetesek hirdetes);
    
    public void updateHirdetes(Hirdetesek hirdetes);
    
    public List<Hirdetesek> getHirdetesek();
    
}
