package ResponseMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by Andrey Melnichenko at 12:19 27-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    private String id;
    private String name;
    private String avatar;
    private String icon;
    private String phone;
    private String time_zone;
    private String account;
    private String email;
    private List<Devices> devices;
}
