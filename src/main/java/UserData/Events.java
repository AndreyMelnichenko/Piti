package UserData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by Andrey Melnichenko at 18:19 23-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Events {
    private boolean success;
    private List<Event> result;
}
