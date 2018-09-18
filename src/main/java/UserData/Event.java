package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 18:23 23-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String date;
    private String actionId;
    private String status;
    private String pin;
    private String deviceId;
    private String id;
    private String message;
    private String type;
    private String view;
}
