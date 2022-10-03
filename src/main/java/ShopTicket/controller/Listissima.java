package ShopTicket.controller;

import ShopTicket.model.Biglietto;
import ShopTicket.model.Evento;

import java.util.ArrayList;


//NON UTILIZZATA

public class Listissima {
    private ArrayList<Evento> eventi;
    private ArrayList<ArrayList<Biglietto>> biglietti;

    public Listissima() {
    }

    public Listissima(ArrayList<Evento> eventi, ArrayList<ArrayList<Biglietto>> biglietti) {
        this.eventi = eventi;
        this.biglietti = biglietti;
    }

    public ArrayList<Evento> getEventi() {
        return eventi;
    }

    public void setEventi(ArrayList<Evento> eventi) {
        this.eventi = eventi;
    }

    public ArrayList<ArrayList<Biglietto>> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(ArrayList<ArrayList<Biglietto>> biglietti) {
        this.biglietti = biglietti;
    }
}
