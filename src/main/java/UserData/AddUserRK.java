package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 11:17 23-08-2018
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRK extends InviteConfirmRK {
    private String role;
    private String email;

/*    private String createdeleteusers;
    private String addremovealldevice;
    private String addremoveowndevice;*/
    private String subunsubdevice;
}