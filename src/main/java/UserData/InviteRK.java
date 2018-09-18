package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteRK {
    private String email;
    private String message;
    private String access;

    private String createdeleteusers;
    private String addremovealldevice;
    private String addremoveowndevice;
}
