package ShopTicket.controller.dto;

public class AcquistoDto {

    private String ora;

    private String stato;

    private Long biglietto;

    private Long utente;

    public AcquistoDto() {
    }

    public AcquistoDto(String ora, String stato, Long biglietto, Long utente) {
        this.ora = ora;
        this.stato = stato;
        this.biglietto = biglietto;
        this.utente = utente;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Long getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Long biglietto) {
        this.biglietto = biglietto;
    }

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }
}
