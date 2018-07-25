package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRK {

    private String email;
    private String password;
    private String passwordConfirm;
    private String time_zone;
    private String lang;

}
