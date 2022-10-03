package ShopTicket.service;

import ShopTicket.controller.dto.AcquistoDto;
import ShopTicket.model.Acquisto;

import java.util.ArrayList;

public interface AcquistoService {
    Acquisto save(AcquistoDto prenotazioneDto);

    Acquisto saves(Acquisto acquisto);
    boolean acquisto_exist(long utente,long biglietto);
    ArrayList<Acquisto> loadAcquistobyid(Long id);

}
