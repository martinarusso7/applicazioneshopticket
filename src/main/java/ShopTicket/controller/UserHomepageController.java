package ShopTicket.controller;

import ShopTicket.controller.dto.ListaAcquistiDto;
import ShopTicket.controller.dto.SaldoDto;
import ShopTicket.controller.dto.User_KeyDto;
import ShopTicket.model.*;
import ShopTicket.repository.*;
import ShopTicket.service.*;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Controller

@RequestMapping("/homepage")
public class UserHomepageController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AcquistoService acquistoService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private BigliettoService bigliettoService;


    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private BigliettoRepository bigliettoRepository;

    @Autowired
    private AcquistoRepository acquistoRepository;

    @Autowired
    private User_KeyServiceImpl user_keyService;

    @Autowired
    private User_KeyRepository user_keyRepository;

    private KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }


    @GetMapping
    public String showHomepage(Model model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            //String email = auth.getName();
            //Utente utente = utenteRepository.findByEmail(email);

        final String id = getSecurityContext().getToken().getPreferredUsername();
        if(!(user_keyService.exists(id))){
            User_KeyDto user_keyDto = new User_KeyDto(id,0);
            user_keyService.save(user_keyDto);
        }

            ArrayList<Evento> listaEventi = (ArrayList<Evento>) eventoRepository.findAll();

            //Nome utente e saldo mi servono per la navbar, la lista corsi per la visualizzazione dei corsi in homepage
            //Nome utente e saldo mi serviranno in tutte le pagine relative all'utente
        final String nome = getSecurityContext().getToken().getName();
        float saldo = user_keyService.loadUserByKey(id).getSaldo();

            model.addAttribute("nome", nome);
            model.addAttribute("saldo", saldo);
            model.addAttribute("eventi", listaEventi);

            return "homemia";
        //} else return "redirect:/login";
    }


    @GetMapping("/riepilogo-acquisti")
    public String viewacquisti(Model model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

           // String email = auth.getName();
            //Utente utente = utenteRepository.findByEmail(email);
        final String user_id = getSecurityContext().getToken().getPreferredUsername();
        final String nome = getSecurityContext().getToken().getName();

            ArrayList<Acquisto> acquistissimi = acquistoRepository.findByIdUtente(user_id);
            ArrayList<ListaAcquistiDto> acquisti = new ArrayList<ListaAcquistiDto>();

            ListaAcquistiDto prenotazione;
            for (int i = 0; i < acquistissimi.size(); i++) {
                prenotazione = new ListaAcquistiDto();
                prenotazione.setEvento(acquistissimi.get(i).getBiglietto().getEvento().getNome());
                prenotazione.setGiorno(acquistissimi.get(i).getBiglietto().getGiorno());
                prenotazione.setOrario(acquistissimi.get(i).getBiglietto().getOrario());
                prenotazione.setLuogo(acquistissimi.get(i).getBiglietto().getLuogo());
                acquisti.add(prenotazione);
            }

            model.addAttribute("prenotazioni", acquisti);
            model.addAttribute("nome", nome);
            float saldo = user_keyService.loadUserByKey(user_id).getSaldo();
            model.addAttribute("saldo", saldo);
            return "riepilogo-acquisti";


        //} else return "redirect:/login";
    }

    @GetMapping("/effettua-acquisto/{id}/{idevento}")
    public String acquista(@PathVariable(value = "id") long id, @PathVariable(value = "idevento") long idevento) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            //String email = auth.getName();
            //Utente utente = userService.loadUserByEmail(email);
            //long iduser = utente.getId();
            //long idbiglietto = id;

            final String id_utente = getSecurityContext().getToken().getPreferredUsername();
            float saldo = user_keyService.loadUserByKey(id_utente).getSaldo();
            User_Key user_key = user_keyService.loadUserByKey(id_utente);

            long idcors = id;
            Biglietto biglietto = bigliettoService.loadBigliettoById(id);

            if (!acquistoService.acquisto_exist(id_utente, idcors)) {

                if (biglietto.getNum_posti_disponibili() > 0) {

                    if (saldo >= biglietto.getCosto()) {

                        user_key.setSaldo(saldo - biglietto.getCosto());
                        biglietto.setNum_posti_disponibili(biglietto.getNum_posti_disponibili() - 1);
                        Acquisto acquisto = new Acquisto(Calendar.getInstance().getTime().toString(), "valido");
                        bigliettoService.saves(biglietto);
                        user_keyRepository.save(user_key);
                        acquisto.setBiglietto(biglietto);
                        acquisto.setUtente(id_utente);
                        acquistoService.saves(acquisto);

                        return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?success0";
                    } else
                        return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?error3";
                } else
                    return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?error2";

            } else return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?error1";
       // } else
         //   return "redirect:/login";
    }

    @GetMapping("/visualizza-biglietti-user/{id}")
    public String showbiglietti(@PathVariable(value = "id") long id, Model model) {
       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       // if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            Evento evento = eventoService.loadEventobyid(id);
            ArrayList<Biglietto> biglietti = bigliettoService.loadBigliettobyEvento(evento.getId());
            // String email = auth.getName();

            final String nome = getSecurityContext().getToken().getName();
            final float saldo = user_keyService.loadUserByKey(getSecurityContext().getToken().getPreferredUsername()).getSaldo();

            //Utente utente = userService.loadUserByEmail(email);
            model.addAttribute("nome", nome);
            model.addAttribute("saldo", saldo);
            model.addAttribute("evento", evento);
            model.addAttribute("biglietti", biglietti);
            model.addAttribute("id", id);

            return "visualizza-biglietti-user";
        //} else
         //   return "redirect:/login";
    }


    @ModelAttribute("ricaric")
    public SaldoDto saldoDto() {
        return new SaldoDto();
    }


    @GetMapping("/carica-saldo")
    public String carica_saldo(Model model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            //String email = auth.getName();
            //Utente utente = utenteRepository.findByEmail(email);

            final String nome = getSecurityContext().getToken().getName();
            final float saldo = user_keyService.loadUserByKey(getSecurityContext().getToken().getPreferredUsername()).getSaldo();

            model.addAttribute("nome", nome);
            model.addAttribute("saldo", saldo);
            return "carica-saldo";
        //} else return "redirect:/login";
    }

    @PostMapping("/carica-saldo")
    public String soldi(Model model, @ModelAttribute("ricaric") SaldoDto saldoDto) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            // Prendo l'ammontare della ricarica inserito e lo aggiungo al saldo dell'utente
            //String email = auth.getName();
            //Utente utente = utenteRepository.findByEmail(email);
            final String nome = getSecurityContext().getToken().getName();
            User_Key user_key = user_keyService.loadUserByKey(getSecurityContext().getToken().getPreferredUsername());
            final float saldo = user_keyService.loadUserByKey(getSecurityContext().getToken().getPreferredUsername()).getSaldo();
            model.addAttribute("nome",nome);
            model.addAttribute("saldo",saldo);


            user_key.setSaldo(saldo+saldoDto.getRicarica());
            user_keyRepository.save(user_key);

            return "redirect:/homepage/carica-saldo?success0";
       // } else
         //   return "redirect:/login";
    }


}

