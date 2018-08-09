package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRK {

    protected String email;
    protected String password;
    protected String passwordConfirm;
    protected String time_zone;
    protected String lang;
}
