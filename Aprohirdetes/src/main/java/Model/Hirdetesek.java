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
    
    @Column(nullable = false, length = 5000)
    private String leiras;
    
    @Column(nullable = false)
    private LocalDate feladasideje;  
    
    @Column(nullable = false)
    private String hely;
    
    @Enumerated(EnumType.STRING)
    private Csomagkuldes csomagkuldes;
    
    @Column(nullable = false)
    private int ar;
    
    @Column(nullable = false)
    private int elado;
    
    @Column(nullable = false)
    private boolean megvasarolva = false;
    
    @Column(nullable = false)
    private String eladoNev;

    public String getEladoNev() {
        return eladoNev;
    }

    public void setEladoNev(String eladoNev) {
        this.eladoNev = eladoNev;
    }
    
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

    public LocalDate getFeladasideje() {
        return feladasideje;
    }

    public void setFeladasideje(LocalDate date) {
        this.feladasideje = date;
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

    public int getElado() {
        return elado;
    }

    public void setElado(int elado) {
        this.elado = elado;
    }

    public boolean isMegvasarolva() {
        return megvasarolva;
    }

    public void setMegvasarolva(boolean megvasarolva) {
        this.megvasarolva = megvasarolva;
    }

    public enum Csomagkuldes {
        IGEN, NEM;
    }
    
    
    
}
