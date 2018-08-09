package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String uid;
    private String auth_token;
    private String refresh_token;
    private String timezone;
    private String lang;
    private String firebase;
    private String role;
    private Access access;

    private String email;
    private String phone;
    private String avatar;
    private String icon;
    private String name;
    private String time_zone;

}
