package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:15 28-08-2018
 */

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Info {
    private String voltage;
    private String gsm;
    private String satellitesCount;
    private String speed;
}
