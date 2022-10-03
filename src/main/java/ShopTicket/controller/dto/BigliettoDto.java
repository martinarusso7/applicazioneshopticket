package ShopTicket.controller.dto;

public class BigliettoDto {

    private String giorno;
    private String orario;

    private String luogo;
    private Integer num_posti_disponibili;
    private float costo;
    private Long evento;
    private Long id;

    public BigliettoDto() {
    }

    public BigliettoDto(String giorno, String orario,  String luogo, Integer num_posti_disponibili, float costo, Long evento) {
        this.giorno = giorno;
        this.orario = orario;
        this.luogo = luogo;
        this.num_posti_disponibili = num_posti_disponibili;
        this.costo = costo;
        this.evento = evento;
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

    public Long getEvento() {return evento;
    }

    public void setEvento(Long evento) {
        this.evento = evento;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
