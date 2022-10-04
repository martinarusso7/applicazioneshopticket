package ShopTicket.service;
import ShopTicket.controller.dto.UserRegistrationDto;
import ShopTicket.model.User_Key;
import ShopTicket.model.Utente;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {

    Utente saves(String id_key,UserRegistrationDto registrationDto);

    //Utente save (User_Key utente);
    boolean email_exists(UserRegistrationDto registrationDto);
    Utente loadUserByEmail(String email) throws UsernameNotFoundException;
    Utente loadUserById(Long id);
}
