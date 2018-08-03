package utils;

import java.util.Arrays;
import java.util.List;

public class RandomIconRoute {
    public static String getRoute() {
        List<String> iconsRoute = Arrays.asList("src/main/resources/car1.png", "src/main/resources/car2.png",
                "src/main/resources/car3.png", "src/main/resources/car4.jpeg",
                "src/main/resources/car5.ico");
        return iconsRoute.get(RandomMinMax.Go(0,4));
    }
}
