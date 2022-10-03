package ShopTicket.repository;

import ShopTicket.controller.dto.BigliettoDto;
import ShopTicket.model.Acquisto;
import ShopTicket.model.Biglietto;
import ShopTicket.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("acquistoRepository")
public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {

    @Query("SELECT p FROM Acquisto p WHERE p.utente.id=?1")
    ArrayList<Acquisto> findByIdUtente(Long id);

    @Query("SELECT p FROM Acquisto p WHERE p.utente.id=?1 and p.biglietto.id=?2")
    Acquisto alreadyprenotato(long utente, long lezione);
}
