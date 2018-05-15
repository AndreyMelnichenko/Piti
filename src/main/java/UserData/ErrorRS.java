package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRS {
    private String success;
    private ErrorMessage error;
}
