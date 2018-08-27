package ResponseMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:28 27-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandAccess {
    private String id;
    private String user_id;
    private String device_id;
    private String users;
    private String sensors;
    private String gsm;
    private String params;
    private String front_reports;
    private String create_reports;
    private String send_command;
    private String device_access_delete;
    private String device_access_change;
    private String device_access_create;
    private String device_delete;
}
