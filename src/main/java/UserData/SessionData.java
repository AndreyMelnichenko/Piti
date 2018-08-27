package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:55 27-08-2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionData {
    private String id;
    private String ip;
    private String client;
    private String date;
    private String email;
}
