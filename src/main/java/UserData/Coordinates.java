package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:17 28-08-2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    private String lat;
    private String lon;
}
