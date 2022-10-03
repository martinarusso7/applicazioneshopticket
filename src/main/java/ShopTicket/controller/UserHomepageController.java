package ShopTicket.controller;

import ShopTicket.controller.dto.ListaAcquistiDto;
import ShopTicket.controller.dto.SaldoDto;
import ShopTicket.model.Acquisto;
import ShopTicket.model.Biglietto;
import ShopTicket.model.Evento;
import ShopTicket.model.Utente;
import ShopTicket.repository.BigliettoRepository;
import ShopTicket.repository.EventoRepository;
import ShopTicket.repository.AcquistoRepository;
import ShopTicket.repository.UtenteRepository;
import ShopTicket.service.AcquistoService;
import ShopTicket.service.BigliettoService;
import ShopTicket.service.EventoService;
import ShopTicket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String showHomepage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            String email = auth.getName();
            Utente utente = utenteRepository.findByEmail(email);

            ArrayList<Evento> listaEventi = (ArrayList<Evento>) eventoRepository.findAll();

            //Nome utente e saldo mi servono per la navbar, la lista corsi per la visualizzazione dei corsi in homepage
            //Nome utente e saldo mi serviranno in tutte le pagine relative all'utente

            model.addAttribute("nome", utente.getNome());
            model.addAttribute("saldo", utente.getSaldo());
            model.addAttribute("eventi", listaEventi);

            return "homemia";
        } else return "redirect:/login";
    }


    @GetMapping("/riepilogo-acquisti")
    public String viewacquisti(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            String email = auth.getName();
            Utente utente = utenteRepository.findByEmail(email);
            ArrayList<Acquisto> acquistissimi = acquistoRepository.findByIdUtente(utente.getId());
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
            model.addAttribute("nome", utente.getNome());
            model.addAttribute("saldo", utente.getSaldo());
            return "riepilogo-acquisti";
        } else return "redirect:/login";
    }

    @GetMapping("/effettua-acquisto/{id}/{idevento}")
    public String acquista(@PathVariable(value = "id") long id, @PathVariable(value = "idevento") long idevento) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            String email = auth.getName();
            Utente utente = userService.loadUserByEmail(email);
            long iduser = utente.getId();
            long idbiglietto = id;
            Biglietto biglietto = bigliettoService.loadBigliettoById(idbiglietto);

            if (!acquistoService.acquisto_exist(iduser, idbiglietto)) {

                if (biglietto.getNum_posti_disponibili() > 0) {

                    if (utente.getSaldo() >= biglietto.getCosto()) {

                        utente.setSaldo(utente.getSaldo() - biglietto.getCosto());
                        biglietto.setNum_posti_disponibili(biglietto.getNum_posti_disponibili() - 1);
                        Acquisto acquisto = new Acquisto(Calendar.getInstance().getTime().toString(), "valido");
                        bigliettoService.saves(biglietto);
                        userService.saves(utente);
                        acquisto.setBiglietto(biglietto);
                        acquisto.setUtente(utente);
                        acquistoService.saves(acquisto);

                        return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?success0";
                    } else
                        return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?error3";
                } else
                    return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?error2";

            } else return "redirect:/homepage/visualizza-biglietti-user/" + idevento + "?error1";
        } else
            return "redirect:/login";
    }

    @GetMapping("/visualizza-biglietti-user/{id}")
    public String showbiglietti(@PathVariable(value = "id") long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            Evento evento = eventoService.loadEventobyid(id);
            ArrayList<Biglietto> biglietti = bigliettoService.loadBigliettobyEvento(evento.getId());
            String email = auth.getName();
            Utente utente = userService.loadUserByEmail(email);
            model.addAttribute("nome", utente.getNome());
            model.addAttribute("saldo", utente.getSaldo());
            model.addAttribute("evento", evento);
            model.addAttribute("biglietti", biglietti);
            model.addAttribute("id", id);

            return "visualizza-biglietti-user";
        } else
            return "redirect:/login";
    }


    @ModelAttribute("ricaric")
    public SaldoDto saldoDto() {
        return new SaldoDto();
    }


    @GetMapping("/carica-saldo")
    public String carica_saldo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            String email = auth.getName();
            Utente utente = utenteRepository.findByEmail(email);
            model.addAttribute("nome", utente.getNome());
            model.addAttribute("saldo", utente.getSaldo());
            return "carica-saldo";
        } else return "redirect:/login";
    }

    @PostMapping("/carica-saldo")
    public String soldi(Model model, @ModelAttribute("ricaric") SaldoDto saldoDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            // Prendo l'ammontare della ricarica inserito e lo aggiungo al saldo dell'utente
            String email = auth.getName();
            Utente utente = utenteRepository.findByEmail(email);
            model.addAttribute("nome", utente.getNome());
            model.addAttribute("saldo", utente.getSaldo());

            float temp = utente.getSaldo();
            utente.setSaldo(temp + saldoDto.getRicarica());
            utenteRepository.save(utente);

            return "redirect:/homepage/carica-saldo?success0";
        } else
            return "redirect:/login";
    }

    // DA IMPLEMENTARE
    /*@GetMapping("/rivendibiglietto/{evento}")
    public String rivendibiglietto(@PathVariable(value = "evento") String evento) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

            String email = auth.getName();
            Utente utente = utenteRepository.findByEmail(email);
            Long idutente = utente.getId();

            ArrayList<Acquisto> acquisti = acquistoRepository.findByIdUtente(idutente);

            System.out.println(acquisti);

            return "riepilogo-acquisti";
        }
        return "redirect:/login";

    }*/

}

