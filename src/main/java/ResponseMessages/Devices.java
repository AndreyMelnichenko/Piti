package ResponseMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:21 27-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Devices {
    private String id;
    private String device_id;
    private String icon;
    private String photo;
    private String name;
    private CommandAccess commandAccess;
}
