package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkResponse {
    public static int getCode(String val){
        int code=0;
        try{
            URL sliderLink = new URL(val);
            HttpURLConnection myUrlCon = (HttpURLConnection) sliderLink.openConnection();
            code = myUrlCon.getResponseCode();
        } catch (IOException e){}
        return code;
    }
}
