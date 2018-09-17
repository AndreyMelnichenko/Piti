package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String is_account;
    private Access access;

    private String email;
    private String phone;
    private String avatar;
    private String icon;
    private String name;
    private String time_zone;

    private String itemCount;
    private String pageCount;
    private List<SessionData> data;

    private String title;
    private String id;
    private String order;
    private String is_show;
    private List<Car> cars;

}
