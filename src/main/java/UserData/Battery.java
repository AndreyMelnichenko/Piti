package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:14 28-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Battery {
    private String status;
    private String procent;
}
