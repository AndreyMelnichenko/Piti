package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:43 23-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassRepareRK extends UserRK {
    private String token;
}
