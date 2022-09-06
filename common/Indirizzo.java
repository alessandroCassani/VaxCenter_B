package common;

public class Indirizzo {

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



}
