package common;

import java.io.Serializable;
import java.util.ArrayList;

public class EventiAvversi implements Serializable {

    private int severita;

    private String note;

    private ArrayList<Sintomatologia> sintomi;

    public EventiAvversi(int severita,String note, ArrayList<Sintomatologia> sintomi){
        this.severita = severita;
        this.note = note;
        this.sintomi = sintomi;
    }

    public int getSeverita(){
        return severita;
    }

    public String getNote(){
        return note;
    }

    public ArrayList<Sintomatologia> getSintomi(){
        return sintomi;
    }

}
