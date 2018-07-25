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

}
