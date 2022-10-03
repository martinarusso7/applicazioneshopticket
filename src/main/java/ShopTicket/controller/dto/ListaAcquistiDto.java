package ShopTicket.controller.dto;

public class ListaAcquistiDto {
    private String evento;
    private String giorno;
    private String orario;

    private String luogo;

    public ListaAcquistiDto() {
    }

    public ListaAcquistiDto(String evento, String giorno, String orario, String luogo) {
        this.evento = evento;
        this.giorno = giorno;
        this.orario = orario;
        this.luogo = luogo;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
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
}
