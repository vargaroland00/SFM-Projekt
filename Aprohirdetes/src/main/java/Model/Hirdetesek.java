package Model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hirdetesek")
public class Hirdetesek 
{
    @Id
    @GeneratedValue()
    int id;
    
    @Column(nullable = false)
    String nev;
    
    @Column(nullable = false)
    String leiras;
    
    @Column(nullable = false)
    LocalDate date;  
    
    @Column(nullable = false)
    String hely;
    
    @Enumerated(EnumType.STRING)
    Csomagkuldes csomagkuldes;
    
    @Column(nullable = false)
    int ar;

    public enum Csomagkuldes {
        IGEN, NEM;
    }
    
}
