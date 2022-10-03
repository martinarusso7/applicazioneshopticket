package ShopTicket.example.demo;

import ShopTicket.repository.EventoRepository;
import ShopTicket.repository.AcquistoRepository;
import ShopTicket.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UtenteRepository utenteRepository;
    private final AcquistoRepository acquistoRepository;
    private final EventoRepository eventoRepository;


    public BootStrapData(UtenteRepository utenteRepository, AcquistoRepository acquistoRepository, EventoRepository eventoRepository) {
        this.utenteRepository = utenteRepository;
        this.acquistoRepository = acquistoRepository;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sono entrato");


        /*Time tempo = new Time(17,30,00);
        String data1 = "21/09/1998";
        String data2 = "22/3/1995";
        Time p1 = new Time(15,00,00);
        Time p2 = new Time(14,30,00);
        Ruolo r1 = new Ruolo("ADMIN");
        Ruolo r2 = new Ruolo("UTENTE");
        Collection collection = new HashSet();
        collection.add(r2);


        Utente Salvatore = new Utente("SNTSVT98P21E791U","Salvatore","Santella",data1,"M","zeus2115@gmail.com","Via Ponte dei cani","3314027048","waka",150,collection,"UTENTE");
        Utente Alex = new Utente("ALXTST98P54E897H","Alessandro","Testa",data2,"M","aletesta@hotmail.it","Via pozzuoli","3345685234","strong",200, collection,"UTENTE");

        Corso PL = new Corso("Powerlifting","Lunedi",tempo,120,3,6);

        Prenotazione P1 = new Prenotazione(p1,"Valida");
        Prenotazione P2 = new Prenotazione(p2,"Valida");

        Salvatore.getPrenotazioni().add(P1);
        P1.setUtente(Salvatore);


        Alex.getPrenotazioni().add(P2);
        P2.setUtente(Alex);


        utenteRepository.save(Salvatore);
        utenteRepository.save(Alex);
        corsoRepository.save(PL);
        prenotazioneRepository.save(P1);
        prenotazioneRepository.save(P2);

        System.out.println("Started in Bootstrap");
        System.out.println("Numero di Corsi: " +corsoRepository.count());
        System.out.println("Prenotazioni Totali: " + prenotazioneRepository.count());

         */

    }
}
