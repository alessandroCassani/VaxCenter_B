import java.io.Serializable;

/**
 * la classe permette la modellazione di un oggetto contenente la provincia e il cap di un comune per cui viene creato questo oggetto
 *
 * @author Alessandro Cassani
 */
public class CapProvincia implements Serializable {

    /**
     * cap del comune
     */
    private final String cap;

    /**
     * provincia del comune
     */
    private final String provincia;

    /**
     * costruttore che permette la creazione di un oggetto passondo ai campi privati cap e provincia
     * i rispettivi valori
     * @param cap cap del comune
     * @param prov sigla provincia dle comune es (VA)
     *
     * @author Alessandro Cassani
     */
    public CapProvincia(String cap,String prov){
        this.cap = cap;
        provincia = prov;
    }

    /**
     * metodo che permette l'output della stringa cap
     * @return string rappresentante il cap
     */
    public String getComune(){return cap;}

    /**
     * metodo che permette l'output della stringa rappresentante la provincia
     * @return stringa rappresentante sigla provincia
     *
     * @author Alessandro Cassani
     */
    public String getProvincia(){return provincia;}
}
