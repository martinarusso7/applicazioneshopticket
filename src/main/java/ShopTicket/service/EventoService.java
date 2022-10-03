package ShopTicket.service;

import ShopTicket.controller.dto.EventoDto;
import ShopTicket.model.Evento;

public interface EventoService {

    Evento save(EventoDto eventoDto);
    Evento aggiorna(EventoDto eventoDto);
    boolean evento_exist(EventoDto eventoDto);
    Evento aggiugi_biglietto(EventoDto eventoDto);
    Evento loadEventobyid(Long id);
    Evento loadEventobynome(String nome);

}
