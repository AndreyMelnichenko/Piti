package ResponseMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRS {
    private String success;
    //@JsonProperty("error")
    //private EditUserError editUserError;
    private String result;
    //private String name;
    //private String file;
    //private String line;
}
