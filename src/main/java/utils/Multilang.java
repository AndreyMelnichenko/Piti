package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * created by Andrey Melnichenko at 12:10 17-08-2018
 */
public class fileParser {
    public static void main(String[] args) throws IOException {
        List<String> myList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/multilanguage.validation"));
        while(scanner.hasNextLine()){
            myList.add(scanner.nextLine());
        }
        System.out.println(myList.toString());
        scanner.close();
    }
}
