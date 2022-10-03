package ShopTicket.controller;

import ShopTicket.controller.dto.BigliettoDto;
import ShopTicket.controller.dto.EventoBigliettoDto;
import ShopTicket.controller.dto.EventoDto;
import ShopTicket.model.Biglietto;
import ShopTicket.model.Evento;
import ShopTicket.repository.BigliettoRepository;
import ShopTicket.repository.EventoRepository;
import ShopTicket.repository.UtenteRepository;
import ShopTicket.service.BigliettoService;
import ShopTicket.service.EventoService;
import ShopTicket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller

@RequestMapping("/home-admin")
public class AdminHomepageController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    BigliettoRepository bigliettoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private BigliettoService bigliettoService;

    public AdminHomepageController() {
        super();
    }

    public AdminHomepageController(UtenteRepository utenteRepository, EventoRepository eventoRepository, BigliettoRepository bigliettoRepository, UserService userService, EventoService eventoService, BigliettoService bigliettoService) {
        super();
        this.utenteRepository = utenteRepository;
        this.eventoRepository = eventoRepository;
        this.bigliettoRepository = bigliettoRepository;
        this.userService = userService;
        this.eventoService = eventoService;
        this.bigliettoService = bigliettoService;
    }
    //Model Attribute
    @ModelAttribute("evento")
    public EventoDto eventoDto() {
        return new EventoDto();
    }

    @ModelAttribute("eventobiglietto")
    public EventoBigliettoDto eventoBigliettoDto(){
        return new EventoBigliettoDto();
    }

    @ModelAttribute("biglietto")
    public BigliettoDto bigliettoDto(){
        return new BigliettoDto();
    }


    //Caricamento Homepage
    @GetMapping
    public String home_admin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            ArrayList<Evento> listaEventi = (ArrayList<Evento>) eventoRepository.findAll();

            ArrayList<ArrayList<Biglietto>> lista_suprema = new ArrayList<ArrayList<Biglietto>>();

            for(int i=0;i<listaEventi.size();i++){
                lista_suprema.add(bigliettoRepository.findByEvento(listaEventi.get(i).getId()));
            }

            Listissima listissima = new Listissima(listaEventi,lista_suprema);
            model.addAttribute("listaEventi", listissima);

            return "home-admin-mia";
        } else
            return "redirect:/login";
    }


    @GetMapping("/inserisci-evento")
    public String showaddevento(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return "inserisci-evento";
        }
        else return "redirect:/login";
    }


    @PostMapping("/inserisci-evento")
    public String aggiungievento(@ModelAttribute("eventobiglietto") EventoBigliettoDto eventoBigliettoDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            //Faccio così perchè altrimenti dava problemi con i campi del dto
            String nom = eventoBigliettoDto.getNome();
            String des = eventoBigliettoDto.getDescrizione();
            String url = eventoBigliettoDto.getUrlimm();
            String giorno = eventoBigliettoDto.getGiorno();
            String orario = eventoBigliettoDto.getOrario();
            String luogo = eventoBigliettoDto.getLuogo();
            Integer num = eventoBigliettoDto.getNum_posti_disponibili();
            float costo = eventoBigliettoDto.getCosto();

            EventoDto eventoDto = new EventoDto(nom,des,url);

          if(eventoService.evento_exist(eventoDto)){
              return "redirect:/home-admin/inserisci-evento?error3";
          }
          eventoService.save(eventoDto);

          //eventoRepository.findByNome(eventoDto.getNome()).getId();

          Long idevento = eventoService.loadEventobynome(eventoDto.getNome()).getId();
          BigliettoDto bigliettoDto = new BigliettoDto(giorno,orario,luogo,num,costo,idevento);
          if(bigliettoService.exist(bigliettoDto)){
              return "redirect:/home-admin/inserisci-evento?error2";
          }
          else{
              bigliettoService.save(bigliettoDto);
          }

          //Ritorno alla pagine di inserimento corso con una notifica di corretto inserimento
          return "redirect:/home-admin/inserisci-evento?success0";
        }
        else
            return "redirect:/login";
    }


    //Modifica di un corso esistente
    @GetMapping("modifica-evento/{id}")
    public String showmodificaevento(@PathVariable (value="id") long id,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            //Cerco il corso da modificare e lo carico insieme alle sue lezioni
            Evento evento = eventoRepository.findbyId(id);
            ArrayList<Biglietto> biglietti = bigliettoRepository.findByEvento(id);

            model.addAttribute("evento",evento);
            model.addAttribute("biglietti",biglietti);

            return "modifica-evento";
        }
        else return "redirect:/login";
    }

    @PostMapping("modifica-evento")
    public String modificaevento(@ModelAttribute("evento")EventoDto eventoDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            Evento evento = eventoRepository.findbyId(eventoDto.getId());
            //Controllo di aver modificato almeno uno dei tre campi del corso (nome,descrizione,urlimm) prima di aggiornarlo
            if(evento.getNome().equals(eventoDto.getNome()) & evento.getDescrizione().equals(eventoDto.getDescrizione()) & evento.getUrlimm().equals(eventoDto.getUrlimm())){
                return "redirect:/home-admin/modifica-evento/"+eventoDto.getId()+"?error3";
            }
            eventoService.aggiorna(eventoDto);
            return "redirect:/home-admin?success0";
        }
        return "redirect:/login";

    }

    @GetMapping("/elimina-evento/{id}")
    public String deleteevento(@PathVariable (value="id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){
            //Cerco il corso da eliminare per id, passato dalla pagina web tramite click e lo elimino
            Evento evento = eventoRepository.findbyId(id);
            eventoRepository.delete(evento);
            return  "redirect:/home-admin?success1";
        }
        return "redorect:/login";
    }


    //LEZIONI

    //Aggiunta di un biglietto ad un evento esistente o appena creato
    @GetMapping("/aggiungi-biglietto/{id}")
    public String addbiglietto(@ModelAttribute("biglietto")BigliettoDto bigliettoDto,@PathVariable (value="id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            //Controllo se esiste già un biglietto che coincide per orario giorno e luogo con quello inserito
            if(bigliettoService.exist(bigliettoDto)){
                return "redirect:/home-admin/modifica-evento/"+id+"?error0";
            }

            else
                bigliettoService.save(bigliettoDto);
        }
        return "redirect:/home-admin/modifica-evento/"+id+"?succes0";

    }

    @GetMapping("/elimina-biglietto/{id}/{idevento}")
    public String deletebiglietto(@PathVariable (value="id") long id,@PathVariable (value="idevento") long idevento){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){
            Biglietto biglietto = bigliettoRepository.findBy_Id(id);
            bigliettoRepository.delete(biglietto);

            return  "redirect:/home-admin/visualizza-biglietti/"+idevento+"?succes0";
        }
        return "redirect:/login";
    }

    //Visualizzazione di tutte le lezioni relative ad un corso esistente
    @GetMapping("/visualizza-biglietti/{id}")
    public String showbiglietti(@PathVariable (value = "id") long id,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            //Visualizzo l'elenco delle leziioni relative ad un determinato corso, da questa pagina possono eliminare lezioni o modificare il corso
            Evento evento = eventoRepository.findbyId(id);
            ArrayList<Biglietto> biglietti = bigliettoRepository.findByEvento(evento.getId());

            model.addAttribute("evento",evento);
            model.addAttribute("biglietti",biglietti);
            model.addAttribute("id", id);

            return "visualizza-biglietti";

        } else
            return "redirect:/login";
    }




}
