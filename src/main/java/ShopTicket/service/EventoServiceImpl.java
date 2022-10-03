package ShopTicket.service;

import ShopTicket.controller.dto.EventoDto;
import ShopTicket.model.Evento;
import ShopTicket.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EventoServiceImpl() {
    }

    public Evento save(EventoDto eventoDto){
        Evento evento = new Evento(eventoDto.getNome(), eventoDto.getDescrizione(), eventoDto.getUrlimm(),Collections.emptyList());
        return eventoRepository.save(evento);
    }

    public Evento aggiorna(EventoDto eventoDto){
        Evento evento = eventoRepository.findbyId(eventoDto.getId());
        evento.setNome(eventoDto.getNome());
        evento.setUrlimm(eventoDto.getUrlimm());
        evento.setDescrizione(eventoDto.getDescrizione());
        return eventoRepository.save(evento);
    }

    public boolean evento_exist(EventoDto eventoDto){
        if (eventoRepository.findByNome(eventoDto.getNome()) != null){
            return true;
        }
        return false;
    }

    public Evento aggiugi_biglietto(EventoDto eventoDto){
        return null;
    }

    @Override
    public Evento loadEventobyid(Long id){
        return eventoRepository.findbyId(id);
    }

    @Override
    public Evento loadEventobynome(String nome){
        Evento evento = eventoRepository.findByNome(nome);

        if(evento == null){
            throw new UsernameNotFoundException("Invalid name corso");
        }
        return evento;
    }
}
