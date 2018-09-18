package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteResult {
    private String id;
    private String invite_id;
    private String name;
    private String avatar;
    private String phone;
    private String time_zone;
    private String account;
    private String pending_invite;
    private String invite_expiries;
    private String email;
    private Access access;
}
