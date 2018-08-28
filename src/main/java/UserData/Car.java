package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Andrey Melnichenko at 12:07 28-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String id;
    private String groop_id;
    private String icon;
    private String photo;
    private String order;
    private String name;
    private String date;
    private String pin;
    private String is_checked;
    private String is_show;
    private String model_id;
    private Icons icons;
    private Info info;
    private Coordinates coordinates;
}
