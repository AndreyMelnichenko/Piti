package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 11:17 23-08-2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRK extends InviteConfirmRK {
    private String role;
    private String email;
}
