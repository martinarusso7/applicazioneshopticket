package ShopTicket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name =  "EVENTI", uniqueConstraints = @UniqueConstraint(columnNames = "id"))


public class Evento {

    @Id
    @Column(name = "id",insertable = false,updatable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false,length = 40)
    private String nome;

    @Column(name = "descrizione",nullable = false)
    private String descrizione;

    @Column(name ="urlimmagine",nullable = false)
    private String urlimm;

    /*@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Abbonamento> lista_abbonamenti;*/

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Biglietto> lista_biglietti;

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                //", lista_abbonamenti=" + lista_abbonamenti +
                ", lista_biglietti=" + lista_biglietti +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id.equals(evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Evento() {

    }

    public Evento(String nome, String descrizione, String urlimm /*List<Abbonamento> lista_abbonamenti*/, List<Biglietto> lista_biglietti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlimm = urlimm;
        this.lista_biglietti = lista_biglietti;

    }

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Biglietto> getLista_biglietti() {
        return lista_biglietti;
    }

    public void setLista_biglietti(List<Biglietto> lista_biglietti) {
        this.lista_biglietti = lista_biglietti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
