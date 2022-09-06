package common;

import java.util.ArrayList;

public class EventiAvversi {

    private int severita;

    private String note;

    private ArrayList<Sintomatologia> sintomi;

    public EventiAvversi(int severita,String note, ArrayList<Sintomatologia> sintomi){
        this.severita = severita;
        this.note = note;
        this.sintomi = sintomi;
    }

}
