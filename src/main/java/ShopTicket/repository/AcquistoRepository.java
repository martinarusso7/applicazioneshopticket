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

    @Query("SELECT p FROM Acquisto p WHERE p.utente=?1")
    ArrayList<Acquisto> findByIdUtente(String id);

    @Query("SELECT p FROM Acquisto p WHERE p.utente=?1 and p.biglietto.id=?2")
    Acquisto alreadyprenotato(String utente, long lezione);

    @Query("SELECT p FROM Acquisto  p WHERE p.biglietto.id=?1")
    ArrayList<Acquisto> findByBiglietto(long id);
}
