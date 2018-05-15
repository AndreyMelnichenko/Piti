package utils;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.PropertiesCache.getProperty;

public class SingUpParser {

    @DataProvider (name="Data collection")
    public Object[][] getCollection() throws IOException {
        ArrayList<String> list = new ArrayList();
        FileInputStream fileWithData = new FileInputStream(getProperty("singup.validdation"));
        DataInputStream data_input = new DataInputStream(fileWithData);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
        String str_line;
        while ((str_line = buffer.readLine()) != null)
        {
            str_line = str_line.trim();
            if ((str_line.length()!=0))
            {
                list.add(str_line);
                System.out.println(str_line);
            }
        }
        Object[][] arr2 = new String[list.size()][5];
        for (int i=0; i<=(list.size()-1);i++) {
            List<String> tmplist = Arrays.asList(list.get(i).split(";"));
            arr2[i][0]=tmplist.get(0);
            arr2[i][1]=tmplist.get(1);
            arr2[i][2]=tmplist.get(2);
            arr2[i][3]=tmplist.get(3);
            arr2[i][4]=tmplist.get(4);
        }
        return arr2;
    }
}
