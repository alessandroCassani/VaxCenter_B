package common;

public enum Tipologia {

    OSPEDALIERO, HUB, AZIENDALE;

    public String getTipologia(Tipologia tipologia){
        String tipo = "";

        switch (tipologia){
            case HUB:
                tipo = "hub";
                break;
            case AZIENDALE:
                tipo = "azindale";
                break;
            case OSPEDALIERO:
                tipo = "ospedaliero";
                break;
        }

        return tipo;
    }
}
