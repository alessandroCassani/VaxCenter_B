package checkdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe CFValidator controlla se il CF inserito dall'operatore vaccinale o dal cittadino sia sintatticamente corretto
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */

public class CFValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String CF_PATTERN = "^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1})$|([0-9]{11})$";

    /**
     * Metodo che permette la validazione del il codice fiscale
     */
    public CFValidator() {
        pattern = Pattern.compile(CF_PATTERN);
    }

    /**
     * Convalida esadecimale con espressione regolare
     *
     * @param hex per validazione
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}