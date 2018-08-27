package ResponseMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by Andrey Melnichenko at 12:16 27-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListRS {
    private boolean success;
    private List<UserList> result;
}
