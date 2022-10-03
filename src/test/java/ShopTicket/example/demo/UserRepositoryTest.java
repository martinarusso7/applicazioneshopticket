package ShopTicket.example.demo;

import ShopTicket.model.*;
import ShopTicket.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Collection;
import java.util.HashSet;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UtenteRepository repo;

    @Autowired
    private EventoRepository repoc;

    @Autowired
    private AcquistoRepository repop;

    @Autowired
    private RuoloRepository repor;

    @Autowired
    private BigliettoRepository repol;

    @Autowired
    private TestEntityManager entityManager;



   //Obiettivo del Test: Aggiungere correttamente un utente, con annesso abbonamento e prenotazione, ed il corso a cui egli è abbonato al database
   @Test
   public void testCreateUser(){
       //Pre condizioni: Non ci sono informazioni riguardo l'utente e il corso inseriti all'interno del database.
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       Ruolo admin = new Ruolo("ADMIN");
       Ruolo utente = new Ruolo("UTENTE");

       Collection collection = new HashSet();
       collection.add(utente);

       Collection collection1 = new HashSet();
       collection1.add(admin);
       collection1.add(utente);

       Utente user = new Utente();
       user.setCf("RSSMTN97S47F839E");
       user.setNome("Martina");
       user.setCognome("Russo");
       user.setEmail("martina.russo21@studenti.unina.it");
       user.setGenere("F");
       user.setData_nascita("07-11-1997");
       user.setIndirizzo("Via Sant'Attanasio 55");
       user.setTelefono("3807571700");
       user.setSaldo(100);
       user.setPassword(passwordEncoder.encode("Password7!"));
       user.setRuoli(collection);
       user.setRuolo("UTENTE");

       Utente user2 = new Utente();
       user2.setCf("MRAMGH97R47E173P");
       user2.setNome("Margherita");
       user2.setCognome("Maria");
       user2.setEmail("margherita.maria@studenti.unina.it");
       user2.setGenere("F");
       user2.setData_nascita("07-10-1997");
       user2.setIndirizzo("Via Fratelli Cervi 4");
       user2.setTelefono("3347825965");
       user2.setSaldo(100);
       user2.setPassword(passwordEncoder.encode("Password7!"));
       user2.setRuoli(collection);
       user2.setRuolo("UTENTE");

       Utente user3 = new Utente();
       user3.setCf("PPEMHL97C63H683U");
       user3.setNome("Michelle");
       user3.setCognome("Pepe");
       user3.setEmail("michelle.pepe@studenti.unina.it");
       user3.setGenere("F");
       user3.setData_nascita("23-03-1997");
       user3.setIndirizzo("Via Parma 58");
       user3.setTelefono("3388423781");
       user3.setSaldo(100);
       user3.setPassword(passwordEncoder.encode("Password7!"));
       user3.setRuoli(collection);
       user3.setRuolo("UTENTE");

       Utente user1 = new Utente();
       user1.setCf("AMMINISTRATORE");
       user1.setNome("AMMINISTRATORE");
       user1.setCognome("AMMINISTRATORE");
       user1.setEmail("admin@admin.admin");
       user1.setGenere("N");
       user1.setData_nascita("AMMINISTRATORE");
       user1.setIndirizzo("AMMINISTRATORE");
       user1.setTelefono("AMMINISTRATORE");
       user1.setSaldo(9999999);
       user1.setPassword(passwordEncoder.encode("admin"));
       user1.setRuoli(collection1);
       user1.setRuolo("ADMIN");


       Evento Coldplay = new Evento();
       Coldplay.setNome("Coldplay");
       Coldplay.setDescrizione("Tour Europe SPHERE. I Coldplay sono un gruppo musicale britannico formatosi a Londra nel 1997.");
       Coldplay.setUrlimm("concerto1.jpg");

       Evento Blanco = new Evento();
       Blanco.setNome("Blanco");
       Blanco.setDescrizione("Tour 2022. Blanco, pseudonimo di Riccardo Fabbriconi, è un cantautore e rapper italiano.");
       Blanco.setUrlimm("concerto2.jpg");

       Evento JovaBeachParty = new Evento();
       JovaBeachParty.setNome("JovaBeachParty");
       JovaBeachParty.setDescrizione("Evento sulle spiagge d'Italia: Jova Beach Party è il primo EP del cantautore italiano Jovanotti.");
       JovaBeachParty.setUrlimm("concerto3.jpg");


       Biglietto BColdplay1 = new Biglietto();
       BColdplay1.setEvento(Coldplay);
       BColdplay1.setGiorno("22/06/2023");
       BColdplay1.setOrario("19:00");
       BColdplay1.setLuogo("Stadio Diego Armando Maradona");
       BColdplay1.setNum_posti_disponibili(50);
       BColdplay1.setCosto(90);

       Biglietto BColdplay2 = new Biglietto();
       BColdplay2.setEvento(Coldplay);
       BColdplay2.setGiorno("23/06/2023");
       BColdplay2.setOrario("19:00");
       BColdplay2.setLuogo("Stadio Diego Armando Maradona");
       BColdplay2.setNum_posti_disponibili(10);
       BColdplay2.setCosto(90);

       Biglietto BColdplay3 = new Biglietto();
       BColdplay3.setEvento(Coldplay);
       BColdplay3.setGiorno("25/06/2023");
       BColdplay3.setOrario("19:00");
       BColdplay3.setLuogo("Stadio San Siro");
       BColdplay3.setNum_posti_disponibili(10);
       BColdplay3.setCosto(90);

       Biglietto BBlanco1 = new Biglietto();
       BBlanco1.setEvento(Blanco);
       BBlanco1.setGiorno("21/06/2023");
       BBlanco1.setOrario("20:30");
       BBlanco1.setLuogo("Rock in Rome");
       BBlanco1.setNum_posti_disponibili(40);
       BBlanco1.setCosto(70);

       Biglietto BBlanco2 = new Biglietto();
       BBlanco2.setEvento(Blanco);
       BBlanco2.setGiorno("28/06/2023");
       BBlanco2.setOrario("20:30");
       BBlanco2.setLuogo("Rock in Firenze");
       BBlanco2.setNum_posti_disponibili(20);
       BBlanco2.setCosto(70);

       Biglietto BJova1 = new Biglietto();
       BJova1.setEvento(JovaBeachParty);
       BJova1.setGiorno("22/07/2023");
       BJova1.setOrario("10:30");
       BJova1.setLuogo("Vasto Marina");
       BJova1.setNum_posti_disponibili(15);
       BJova1.setCosto(100);

       Biglietto BJova2 = new Biglietto();
       BJova2.setEvento(JovaBeachParty);
       BJova2.setGiorno("22/09/2023");
       BJova2.setOrario("10:30");
       BJova2.setLuogo("Castel Volturno");
       BJova2.setNum_posti_disponibili(15);
       BJova2.setCosto(100);

       /*Acquisto P1 = new Acquisto();
       P1.setUtente(user);
       P1.setOra("19:00:00");
       P1.setStato("valido");
       P1.setBiglietto(BColdplay1);*/


       Ruolo savedRuolo = repor.save(admin);
       Ruolo savedRuolo1 = repor.save(utente);

       Utente savedAdmin = repo.save(user1);
       Utente savedUser = repo.save(user);
       Utente saved1 = repo.save(user2);
       Utente saved2 = repo.save(user3);

       Evento savedEvento = repoc.save(Coldplay);
       Evento savedEvento2 = repoc.save(Blanco);
       Evento savedEvento3 = repoc.save(JovaBeachParty);

       Biglietto savedBiglietto = repol.save(BColdplay1);
       Biglietto save1 = repol.save(BColdplay2);
       Biglietto save2 = repol.save(BColdplay3);
       Biglietto save3 = repol.save(BBlanco1);
       Biglietto save4 = repol.save(BBlanco2);
       Biglietto save5 = repol.save(BJova1);
       Biglietto save6 = repol.save(BJova2);

       //Acquisto savedAcquisto = repop.save(P1);


      Ruolo existRuolo = entityManager.find(Ruolo.class,savedRuolo.getId());
      Ruolo existRuolo1 = entityManager.find(Ruolo.class,savedRuolo1.getId());
      Utente existAdmin = entityManager.find(Utente.class,savedAdmin.getId());
      Utente existUser = entityManager.find(Utente.class,savedUser.getId());
      Evento existEvento = entityManager.find(Evento.class, savedEvento.getId());

      //Acquisto existAcquisto = entityManager.find(Acquisto.class, savedAcquisto.getId());



      assert(existRuolo.getNome().equals(admin.getNome()));
      assert(existRuolo1.getNome().equals(utente.getNome()));
      assert(existAdmin.getId().equals(admin.getId()));
      assert(existUser.getEmail()).equals(user.getEmail());

      assert(existEvento.getId().equals(Coldplay.getId()));
      //assert(existAcquisto.getUtente().equals(P1.getUtente()));



   }
}
