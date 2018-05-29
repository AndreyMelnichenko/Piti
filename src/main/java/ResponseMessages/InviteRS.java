package ResponseMessages;

import UserData.InviteResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteRS {
    private boolean success;
    private InviteResult result;
}
