package ShopTicket.repository;

import ShopTicket.model.User_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("user_keyRepository")
public interface User_KeyRepository extends JpaRepository<User_Key,Long> {
    @Query("SELECT p FROM User_Key p WHERE p.id=?1")
    User_Key findbyId(Long id);

    @Query("SELECT p FROM User_Key p WHERE p.key_id=?1")
    User_Key findbykey(String key_id);
}