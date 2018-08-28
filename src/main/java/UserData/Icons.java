package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:13 28-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Icons {
    private String power;
    private String locked;
    private Battery battery;
}
