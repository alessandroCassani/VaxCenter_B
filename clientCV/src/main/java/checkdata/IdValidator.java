package checkdata;

/**
 * la classe fornisce il metodo di controllo per la lunghezza dell'id inserito dall'utente
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 * */
public class IdValidator {

    /**
     * Metodo che permette la validazione dell'Id
     */
    public IdValidator(){}

    /**
     * il metodo permette il controllo che la stringa rappresentante i bit dell'id univoco sia formata da 16 numeri
     * @param id id della persona
     * @return true o false in base che la stringa sia formata da 16 caratteri
     *
     * @author Alessandro Cassani
     */
    public boolean checkdata(String id){
        return id.length()==16;
    }
}