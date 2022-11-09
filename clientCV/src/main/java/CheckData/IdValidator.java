package CheckData;

/**
 * la classe fornisce il emtodo di controllo per la lunghezza dell'id inserito dall'utente
 *
 * @author Alessandro cassani
 * */
public class IdValidator {
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