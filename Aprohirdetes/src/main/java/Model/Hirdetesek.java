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
@Table(name = "Hirdetesek")
public class Hirdetesek 
{
    @Id
    @GeneratedValue()
    private int id;
    
    @Column(nullable = false)
    private String nev;
    
    @Column(nullable = false)
    private String leiras;
    
    @Column(nullable = false)
    private LocalDate date;  
    
    @Column(nullable = false)
    private String hely;
    
    @Enumerated(EnumType.STRING)
    private Csomagkuldes csomagkuldes;
    
    @Column(nullable = false)
    private int ar;

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHely() {
        return hely;
    }

    public void setHely(String hely) {
        this.hely = hely;
    }

    public Csomagkuldes getCsomagkuldes() {
        return csomagkuldes;
    }

    public void setCsomagkuldes(Csomagkuldes csomagkuldes) {
        this.csomagkuldes = csomagkuldes;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public enum Csomagkuldes {
        IGEN, NEM;
    }
    
    
    
}
