package ShopTicket.controller;


import ShopTicket.model.Evento;
import ShopTicket.repository.EventoRepository;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private EventoRepository eventoRepository;

        private KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        }

        @GetMapping("/login")
        public ModelAndView login(Model model) {

            Set<String> ruolo = getSecurityContext().getToken().getRealmAccess().getRoles();
            if (ruolo.contains("Admin")){
                return new ModelAndView("redirect:/home-admin");
            }

            ArrayList<Evento> listaEventi = (ArrayList<Evento>) eventoRepository.findAll();
            model.addAttribute("eventi", listaEventi);
            final String nome = getSecurityContext().getToken().getPreferredUsername();
            model.addAttribute("nome",nome);

            return new ModelAndView("redirect:/homepage");

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
        public ModelAndView homepage(Model model) {

            Set<String> ruolo = getSecurityContext().getToken().getRealmAccess().getRoles();
            if (ruolo.contains("Admin")){
                return new ModelAndView("redirect:/home-admin");
            }

            ArrayList<Evento> listaEventi = (ArrayList<Evento>) eventoRepository.findAll();
            model.addAttribute("eventi", listaEventi);
            final String nome = getSecurityContext().getToken().getPreferredUsername();
            model.addAttribute("nome",nome);

            return new ModelAndView("redirect:/homepage");

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
