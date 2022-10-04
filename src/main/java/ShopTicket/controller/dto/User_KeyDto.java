package ShopTicket.controller.dto;

public class User_KeyDto {
    private String key_id;
    private float saldo;

    public User_KeyDto() {
    }

    public User_KeyDto(String key_id, float saldo) {
        this.key_id = key_id;
        this.saldo = saldo;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
