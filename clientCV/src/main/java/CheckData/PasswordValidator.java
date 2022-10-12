package CheckData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe PasswordValidator controlla se la Password inserita dal cittadino rispetta i requisiti
 *
 * Lunghezza compresa tra 8 e 20 caratteri
 * Almeno una lettera maiuscola e una minuscola
 * Almeno un numero
 * Almeno un carattere speciale come ! @ # & ( )
 *
 * @author Paolo Bruscagin
 */

public class PasswordValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
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