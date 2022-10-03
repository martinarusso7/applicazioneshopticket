package ShopTicket.service;

import ShopTicket.controller.dto.AcquistoDto;
import ShopTicket.model.Acquisto;
import ShopTicket.repository.BigliettoRepository;
import ShopTicket.repository.AcquistoRepository;
import ShopTicket.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AcquistoServiceImpl implements AcquistoService {
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private BigliettoRepository bigliettoRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public AcquistoServiceImpl(AcquistoRepository acquistoRepository) {
        this.acquistoRepository = acquistoRepository;
    }

    public AcquistoServiceImpl() {
    }

    public Acquisto save(AcquistoDto prenotazioneDto){
        Acquisto p = new Acquisto(prenotazioneDto.getOra(),prenotazioneDto.getStato());
        p.setBiglietto(bigliettoRepository.findBy_Id(prenotazioneDto.getBiglietto()));
        p.setUtente(utenteRepository.findbyId_utente(prenotazioneDto.getUtente()));
        return acquistoRepository.save(p);
    }

    @Override
    public Acquisto saves(Acquisto acquisto) {
        return acquistoRepository.save(acquisto);
    }

    public boolean acquisto_exist(long utente, long biglietto){
        Acquisto acquisto = acquistoRepository.alreadyprenotato(utente,biglietto);
        if(acquisto != null)
            return true;
        else
            return false;
    }

    public ArrayList<Acquisto> loadAcquistobyid(Long id){
        return acquistoRepository.findByIdUtente(id);
    }


}
