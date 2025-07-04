package hu.ingatlan.kereso.entity;

import java.io.Serializable;

/**
 *
 * @author Böröczky Csaba
 */
public class IngatlanAdat implements Serializable {
    
    private String tulajdonos;
    private String helyrajziSzam;
    private Long alapterulet;
    private Long becsultErtek;

    public IngatlanAdat() {
        
    }
    
    public IngatlanAdat(String tulajdonos, String helyrajziSzam, Long alapterulet, Long becsultErtek) {
        this.tulajdonos = tulajdonos;
        this.helyrajziSzam = helyrajziSzam;
        this.alapterulet = alapterulet;
        this.becsultErtek = becsultErtek;
    }

    
    public String getTulajdonos() {
        return tulajdonos;
    }

    public void setTulajdonos(String tulajdonos) {
        this.tulajdonos = tulajdonos;
    }

    public String getHelyrajziSzam() {
        return helyrajziSzam;
    }

    public void setHelyrajziSzam(String helyrajziSzam) {
        this.helyrajziSzam = helyrajziSzam;
    }

    public Long getAlapterulet() {
        return alapterulet;
    }

    public void setAlapterulet(Long alapterulet) {
        this.alapterulet = alapterulet;
    }

    public Long getBecsultErtek() {
        return becsultErtek;
    }

    public void setBecsultErtek(Long becsultErtek) {
        this.becsultErtek = becsultErtek;
    }
    
    
}
