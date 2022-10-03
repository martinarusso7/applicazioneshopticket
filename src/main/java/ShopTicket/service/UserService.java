package ShopTicket.service;
import ShopTicket.controller.dto.UserRegistrationDto;
import ShopTicket.model.Utente;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {

    Utente save(UserRegistrationDto registrationDto);

    Utente saves (Utente utente);
    boolean email_exists(UserRegistrationDto registrationDto);
    Utente loadUserByEmail(String email) throws UsernameNotFoundException;
    Utente loadUserById(Long id);
}
