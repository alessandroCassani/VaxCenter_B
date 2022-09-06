package common;

public enum Sintomatologia {

    MALDITESTA, FEBBRE, DOLORI_MA, LINFOADENOPATIA, TACHICARDIA, CRISIPERTENSIVA;

    public String getSintomatologia(Sintomatologia sintomatologia){
        String sintomo = "";

        switch (sintomatologia){
            case MALDITESTA:
                sintomo = "mal di testa";
                break;
            case FEBBRE:
                sintomo = "febbre";
                break;
            case DOLORI_MA:
                sintomo = "dolori muscolari e articolari";
                break;
            case LINFOADENOPATIA:
                sintomo = "linfoadenopatia";
                break;
            case TACHICARDIA:
                sintomo = "tachicardia";
                break;
            case CRISIPERTENSIVA:
                sintomo = "crisi ipertensiva";
                break;
        }

        return sintomo;
    }
}
