package ShopTicket.controller.dto;

public class EventoBigliettoDto {
    private String nome;
    private String descrizione;
    private String urlimm;
    private String giorno;
    private String orario;

    private String luogo;
    private Integer num_posti_disponibili;
    private float costo;


    public EventoBigliettoDto() {

    }

    public EventoBigliettoDto(String nome, String descrizione, String urlimm, String giorno, String orario, String luogo, Integer num_posti_disponibili, float costo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlimm = urlimm;
        this.giorno = giorno;
        this.orario = orario;
        this.luogo = luogo;
        this.num_posti_disponibili = num_posti_disponibili;
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
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
        this.orario = orario;
    }


    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Integer getNum_posti_disponibili() {
        return num_posti_disponibili;
    }

    public void setNum_posti_disponibili(Integer num_posti_disponibili) {
        this.num_posti_disponibili = num_posti_disponibili;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}


