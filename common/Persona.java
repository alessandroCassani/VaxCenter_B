package common;

import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable {

    private String nome;

    private String cognome;

    private String codFisc;

    private String email;

    private String id;

    private Date dataNascita;

    private CentroVaccinale centroVaccinale;

    public Persona(){}

    public Persona(String nome,String cognome, String codFisc,String email, String id, Date dataNascita, CentroVaccinale centroVaccinale){
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.email = email;
        this.id = id;
        this.dataNascita = dataNascita;
        this.centroVaccinale = centroVaccinale;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }

    public String getCodFisc(){
        return codFisc;
    }

    public String getEmail(){
        return email;
    }

    public String getId(){
        return id;
    }

    public Date getDataNascita(){
        return dataNascita;
    }

    public CentroVaccinale getCentroVaccinale(){
        return centroVaccinale;
    }

}
