package UserData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Access {
    @JsonProperty("/home/map")
    private String mainPage;
    @JsonProperty("add_device")
    private String addDevice;
    @JsonProperty("change_grooup")
    private String changeGrooup;
    @JsonProperty("filter_period")
    private String filterPeriod;
    @JsonProperty("filter_action_and_travel")
    private String filterActionAndTravel;
    @JsonProperty("settings_map")
    private String settingsMap;
    @JsonProperty("/account/users")
    private String accountUser;
    @JsonProperty("add_user")
    private String addUser;
    @JsonProperty("invite_user")
    private String inviteUser;
    @JsonProperty("delete_user")
    private String deleteUser;
    @JsonProperty("remove_user_from_device")
    private String removeUserFromDevice;
    @JsonProperty("change_access_to_device")
    private String changeAccessToDevice;
    @JsonProperty("change_user_data")
    private String changeUserData;
    @JsonProperty("add_access_user_to_device")
    private String addAccessUserToDevice;
    @JsonProperty("/account/devices")
    private String accountDevices;
    @JsonProperty("remove_device")
    private String removeDevice;
    private String createdeleteusers;
    private String addremovealldevice;
    private String addremoveowndevice;
}
