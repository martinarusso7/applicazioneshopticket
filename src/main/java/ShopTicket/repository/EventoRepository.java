package ShopTicket.repository;

import ShopTicket.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("corsoRepository")
public interface EventoRepository extends JpaRepository<Evento,Long> {
    Evento findByNome(String nome);

    @Query("SELECT p FROM Evento p WHERE p.id=?1 ")
    Evento findbyId(Long id);



}
