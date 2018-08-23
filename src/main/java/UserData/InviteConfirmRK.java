package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 17:55 22-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteConfirmRK {
    private String phone;
    private String password;
    private String passwordConfirm;
    private String time_zone;
    private String lang;
    private String name;
}
