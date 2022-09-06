package common;

import java.util.Date;

public class Cittadino extends Persona {

    private Account account;

    public Cittadino(String nome, String cognome, String codFisc, String email, String id, Date dataNascita, CentroVaccinale centroVaccinale, Account account){
        super(nome,cognome,codFisc,email,id,dataNascita,centroVaccinale);
        this.account = account;
    }

}

