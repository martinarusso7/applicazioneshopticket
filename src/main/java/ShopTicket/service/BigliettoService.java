package ShopTicket.service;

import ShopTicket.controller.dto.BigliettoDto;
import ShopTicket.model.Biglietto;

import java.util.ArrayList;

public interface BigliettoService {

    Biglietto save(BigliettoDto bigliettoDto);

    Biglietto saves(Biglietto biglietto);

    boolean exist(BigliettoDto bigliettoDto);
    Biglietto aggiungi_prenotazione(BigliettoDto bigliettoDto);
    ArrayList<Biglietto> loadBigliettobyEvento(Long idevento);

    Biglietto loadBigliettoById(Long idbiglietto);
}
