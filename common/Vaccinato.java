package common;

import java.util.Date;

public class Vaccinato extends Persona {

    private Date dataSomministrazione;

    private Vaccino vaccino;

    public Vaccinato(String nome,String cognome, String codFisc,String email, String id, Date dataNascita, CentroVaccinale centroVaccinale, Date dataSomministrazione, Vaccino vaccino) {
        super(nome,cognome,codFisc,email,id,dataNascita,centroVaccinale);
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
    }
}
