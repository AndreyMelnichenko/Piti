package utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * created by Andrey Melnichenko at 12:10 17-08-2018
 */
public class Multilang {
    public List<String> getErrorClassNames() throws FileNotFoundException{
        List<String> myList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/multilanguage.validation"));
        while(scanner.hasNextLine()){
            myList.add(scanner.nextLine());
        }
        scanner.close();
        return myList;
    }
    public void scanPage() throws FileNotFoundException {
        List<String> errorList = new ArrayList<>();
        System.out.println(getWebDriver().getCurrentUrl());
        Multilang lang = new Multilang();
        lang.getErrorClassNames();
        for (String error : lang.getErrorClassNames()){
            for(SelenideElement element: $$(By.xpath("//*[contains(text(), '"+error+"')]"))) {
                System.out.println(element.parent());
                errorList.add(element.parent().toString());
            }
        }
    }
}
