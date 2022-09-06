package common;

import java.io.Serializable;

public class Indirizzo implements Serializable {

    private Qualificatore qualificatore;

    private String nome;

    private String civico;

    private String comune;

    private String provincia;

    private int cap;

    public Indirizzo(Qualificatore qualificatore,String nome,String civico,String comune,String provincia,int cap){
            this.qualificatore = qualificatore;
            this.nome = nome;
            this.civico = civico;
            this.comune = comune;
            this.provincia = provincia;
            this.cap = cap;
    }

    public Qualificatore getQualificatore(){
        return qualificatore;
    }

    public String getNome(){
        return nome;
    }

    public String getCivico(){
        return civico;
    }

    public String getComune(){
        return comune;
    }

    public String getProvincia(){
        return provincia;
    }

    public int getCap(){
        return cap;
    }

}
