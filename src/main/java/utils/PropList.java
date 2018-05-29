package utils;

import java.util.HashMap;
import java.util.Map;

public class PropList {

   private static final Map<String, String> MapList = propertiesFile();
   public static Map<String, String> propertiesFile()
   {
      Map<String,String> MapList = new HashMap<String,String>();
      MapList.put("global", "test.properties");
      MapList.put("local", "data.properties");
      return MapList;
   }

   public static void main(String[] args) {
      System.out.println(propertiesFile().get("local"));
   }
}
