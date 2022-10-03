package ShopTicket.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="BIGLIETTI",uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Biglietto {

    @Id
    @Column(name = "id",insertable = false,updatable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "giorno",nullable = false,length = 10)
    private String giorno;

    @Column(name = "orario_corso",nullable = false)
    private String orario;


    @Column(name = "sala",nullable = false)
    private String luogo;

    @Column(name = "num_posti_disponibili",nullable = false)
    private Integer num_posti_disponibili;

    @Column(name = "costo",nullable = false)
    private float costo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Evento evento;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Acquisto> lista_prenotazioni;

    public Biglietto() {
    }

    public Biglietto(String giorno, String orario, String luogo, Integer num_posti_disponibili, float costo, Evento evento, List<Acquisto> lista_prenotazioni) {
        this.giorno = giorno;
        this.orario = orario;
        this.luogo = luogo;
        this.num_posti_disponibili = num_posti_disponibili;
        this.costo=costo;
        this.evento = evento;
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public Integer getNum_posti_disponibili() {
        return num_posti_disponibili;
    }

    public void setNum_posti_disponibili(Integer num_posti_disponibili) {
        this.num_posti_disponibili = num_posti_disponibili;
    }

    public List<Acquisto> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    public void setLista_prenotazioni(List<Acquisto> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario= orario;
    }


    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", giorno='" + giorno + '\'' +
                ", orario_evento=" + orario +
                ", luogo=" + luogo +
                ", num_posti_disponibili=" + num_posti_disponibili +
                ", evento=" + evento +
                ", lista_prenotazioni=" + lista_prenotazioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biglietto biglietto = (Biglietto) o;
        return giorno.equals(biglietto.giorno) && orario.equals(biglietto.orario) && luogo.equals(biglietto.luogo) && evento.equals(biglietto.evento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giorno, orario, luogo, evento);
    }
}
