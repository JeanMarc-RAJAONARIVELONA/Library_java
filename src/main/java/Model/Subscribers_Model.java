package Model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Subscribers_Model extends User{
    private String references;
    public Subscribers_Model(int id_User, String user_name, String user_email, String references) {
        super(id_User, user_name, user_email);
        this.references = references;
    }
}
