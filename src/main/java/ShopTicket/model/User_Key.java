package ShopTicket.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name =  "USERS", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class User_Key {
    @Id
    @Column(name = "id",insertable = false,updatable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_id",nullable=false)
    private String key_id;

    @Column(name = "saldo",nullable = false,length = 20)
    private float saldo;


    public User_Key() {
    }

    public User_Key(String key_id, float saldo) {
        this.key_id = key_id;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User_Key{" +
                "id=" + id +
                ", key_id='" + key_id + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Key user_key = (User_Key) o;
        return Float.compare(user_key.saldo, saldo) == 0 && Objects.equals(id, user_key.id) && Objects.equals(key_id, user_key.key_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key_id, saldo);
    }
}

