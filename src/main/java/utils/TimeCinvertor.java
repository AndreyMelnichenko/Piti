package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by Andrey Melnichenko at 17:30 20-08-2018
 */
public class TimeCinvertor {
    public int getDiff(String before, String after) throws ParseException {
        System.out.println(before+"==="+after);
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date1 = parser.parse(before);
        Date date2 = parser.parse(after);
        System.out.println("Before date "+date1);
        System.out.println("After date "+date2);
        SimpleDateFormat onlyHour = new SimpleDateFormat("HH");
        String resul1 = onlyHour.format(date1);
        System.out.println("Parsed ACTUAL HOURS: "+resul1);
        String resul2 = onlyHour.format(date2);
        System.out.println("Parsed Expected HOURS: "+resul2);
        System.out.println(Integer.parseInt(resul1)+"==="+Integer.parseInt(resul2));
        return (Integer.parseInt(resul2)-Integer.parseInt(resul1));
    }
}
