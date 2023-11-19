package Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id_User;
    private String user_name;
    private String user_email;
}
