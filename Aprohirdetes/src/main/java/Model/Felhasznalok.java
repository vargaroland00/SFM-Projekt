package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Felhasznalok")
public class Felhasznalok 
{
    @Id
    @Column(name = "felhasznalo_id")
    @GeneratedValue()
    private int id;
    
    @Column(nullable = false)
    private String nev;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false, length = 512)
    private String jelszo;
    
    @Column(nullable = false)
    private String salt;
    
    @Column(nullable = false)
    private String telefonszam;
    
    @Enumerated(EnumType.STRING)
    private Jogosultsag jogosultsag;

    public Jogosultsag getJogosultsag() {
        return jogosultsag;
    }

    public void setJogosultsag(String jogosultsag) 
    {
        if (jogosultsag.toUpperCase().equals(this.jogosultsag.ADMIN.toString()))
        {
            this.jogosultsag = this.jogosultsag.ADMIN;
        }
        else 
        {
           this.jogosultsag = this.jogosultsag.FELHASZNALO;
        }
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }
    
    public enum Jogosultsag {
        ADMIN, FELHASZNALO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
