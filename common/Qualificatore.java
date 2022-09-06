package common;

public enum Qualificatore {

    VIA,VIALE,PIAZZA;

    public String getQualificatore(Qualificatore qualificatore){
        String qualif = "";

        switch (qualificatore){
            case VIA:
                qualif = "via";
                break;
            case VIALE:
                qualif = "viale";
                break;
            case PIAZZA:
                qualif = "piazza";
                break;
        }

        return qualif;
    }

}
