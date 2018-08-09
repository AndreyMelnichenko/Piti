package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingsRK {

    private String email;
    private String name;
    private String time_zone;
    private String lang;
    private String phone;
}
