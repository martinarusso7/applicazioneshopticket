package ShopTicket.service;

import ShopTicket.controller.dto.BigliettoDto;
import ShopTicket.model.Biglietto;
import ShopTicket.repository.EventoRepository;
import ShopTicket.repository.BigliettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class BigliettoServiceImpl implements BigliettoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private BigliettoRepository bigliettoRepository;

    public Biglietto save(BigliettoDto bigliettoDto){
        Biglietto biglietto = new Biglietto(bigliettoDto.getGiorno(), bigliettoDto.getOrario(), bigliettoDto.getLuogo(), bigliettoDto.getNum_posti_disponibili(), bigliettoDto.getCosto(), eventoRepository.findbyId(bigliettoDto.getEvento()), Collections.emptyList());
        return bigliettoRepository.save(biglietto);
    }

    @Override
    public Biglietto saves(Biglietto biglietto) {
        return bigliettoRepository.save(biglietto);
    }

    public boolean exist(BigliettoDto bigliettoDto){
        //if(lezioneRepository.findBySala(lezioneDto.getSala()) != null && lezioneRepository.findByGiorno(lezioneDto.getGiorno()) != null && lezioneRepository.findByOrario(lezioneDto.getOrario()) !=null){
        //Carico tutte le lezioni nella sala specificata per un determinato giorno
        ArrayList<Biglietto> listlezioni = bigliettoRepository.findSovrapponi(bigliettoDto.getGiorno(), bigliettoDto.getLuogo());
        if(!listlezioni.isEmpty()){
            String[] hourMin1 = bigliettoDto.getOrario().split(":");
            int hour1 = Integer.parseInt(hourMin1[0]);
            int mins1 = Integer.parseInt(hourMin1[1]);
            int hoursInMins1 = hour1 * 60;
            int value1 = hoursInMins1+mins1;
            //int dur1 = lezioneDto.getDurata();
            return true;
        }
            return false;

    }

    public Biglietto aggiungi_prenotazione(BigliettoDto bigliettoDto){
        return null;
    }

    public ArrayList<Biglietto> loadBigliettobyEvento(Long idevento){
        return bigliettoRepository.findByEvento(idevento);
    }

    public Biglietto loadBigliettoById(Long idbiglietto){return bigliettoRepository.findBy_Id(idbiglietto);}
}
