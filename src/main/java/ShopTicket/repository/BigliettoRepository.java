package ShopTicket.repository;

import ShopTicket.model.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("bigliettorepository")
public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {
    ArrayList<Biglietto>findByGiorno(String giorno);
    ArrayList<Biglietto>findByOrario(String orario);
    ArrayList<Biglietto>findByLuogo(String luogo);

    @Query("SELECT p FROM Biglietto p WHERE p.evento.id=?1")
    ArrayList<Biglietto>findByEvento(Long id_evento);

    @Query("SELECT p FROM Biglietto p WHERE p.giorno=?1 AND p.luogo=?2")
    ArrayList<Biglietto> findSovrapponi(String giorno, String luogo);

    @Query("SELECT p FROM Biglietto p WHERE p.id=?1")
    Biglietto findBy_Id(Long id);

}
