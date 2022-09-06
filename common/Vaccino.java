package common;

public enum Vaccino {

    PFIZER, ASTRAZENECA, MODERNA, JOHNSON;

    public String getVaccino(Vaccino vaccino){
        String vax = "";

        switch(vaccino){
            case PFIZER:
                vax = "pfizer";
                break;
            case JOHNSON:
                vax = "j&j";
                break;
            case MODERNA:
                vax = "moderna";
                break;
            case ASTRAZENECA:
                vax = "astrazeneca";
                break;
        }
        return vax;
    }

}
