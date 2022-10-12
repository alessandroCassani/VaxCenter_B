package CheckData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe EmailValidator controlla se la l'indirizzo Email inserito dal cittadino sia sintatticamente corretto e con dominio valido
 *
 * @author Paolo Bruscagin
 */

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
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