public class CapComuneProvincia {

    private String comune;

    private String provincia;

    private int cap;

    public CapComuneProvincia(String com,String prov,int cap){
        comune = com;
        provincia = prov;
        this.cap = cap;
    }

    public String getComune(){return comune;}
    public String getProvincia(){return provincia;}
    public int getCap(){return cap;}
}
