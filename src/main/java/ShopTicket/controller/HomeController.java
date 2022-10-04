package ShopTicket.controller;


import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class HomeController {

        private KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        }

        @GetMapping("/login")
        public String login() {

            Set<String> ruolo = getSecurityContext().getToken().getRealmAccess().getRoles();
            if (ruolo.contains("Admin")){
                return "redirect:/home-admin";
            }

            return "redirect:/homepage";

            /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

                return "redirect:/home-admin";
            }
            else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

                return "redirect:/homepage";
            }
            else
                return "login";*/
        }

        @GetMapping("/")
        public String homepage() {

            Set<String> ruolo = getSecurityContext().getToken().getRealmAccess().getRoles();
            if (ruolo.contains("Admin")){
                return "redirect:/home-admin";
            }

            return "redirect:/homepage";

            /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

                return "redirect:/home-admin";
            }
            else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

                return "redirect:/homepage";
            }
            else
                return "login";*/
        }



}
