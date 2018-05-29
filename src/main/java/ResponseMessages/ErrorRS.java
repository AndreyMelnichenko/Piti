package ResponseMessages;

import UserData.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRS {
    private boolean success;
    private ErrorMessage error;
}
