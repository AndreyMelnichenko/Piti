package UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by Andrey Melnichenko at 10:18 27-08-2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDeleteRK {
    private List<String> eid;
}
