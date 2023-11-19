package Model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Subscribers_Model extends User{
    private int id_subscribers;
    private String subscribers_name;
    private String subscribers_email;
    private String references;

    public Subscribers_Model(int id_User, String user_name, String user_email) {
        super(id_User, user_name, user_email);
    }

    public Subscribers_Model(int id_User, String user_name, String user_email, String references) {
        super(id_User, user_name, user_email);
        this.references = references;
    }
}
