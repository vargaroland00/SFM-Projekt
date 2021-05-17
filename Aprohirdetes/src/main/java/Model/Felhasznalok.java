package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private String szamlazasi_cim;
    
    @Column(nullable = false)
    private String szallitasi_cim;

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

    public String getSzamlazasi_cim() {
        return szamlazasi_cim;
    }

    public void setSzamlazasi_cim(String szamlazasi_cim) {
        this.szamlazasi_cim = szamlazasi_cim;
    }

    public String getSzallitasi_cim() {
        return szallitasi_cim;
    }

    public void setSzallitasi_cim(String szallitasi_cim) {
        this.szallitasi_cim = szallitasi_cim;
    }
    
    
}
