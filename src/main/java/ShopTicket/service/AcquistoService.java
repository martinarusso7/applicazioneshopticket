package ShopTicket.service;

import ShopTicket.controller.dto.AcquistoDto;
import ShopTicket.model.Acquisto;

import java.util.ArrayList;

public interface AcquistoService {
    Acquisto save(AcquistoDto prenotazioneDto, String utente);

    Acquisto saves(Acquisto acquisto);
    boolean acquisto_exist(String utente,long biglietto);
    ArrayList<Acquisto> loadAcquistobyid(String id);

}
