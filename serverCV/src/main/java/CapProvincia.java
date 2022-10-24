public class CapProvincia {

    private String cap;

    private String provincia;


    public CapProvincia(String cap,String prov){
        this.cap = cap;
        provincia = prov;
    }

    public String getComune(){return cap;}
    public String getProvincia(){return provincia;}
}
