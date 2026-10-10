package ecom.swaglabs.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonData {

    public String getData(String data) throws IOException {

        FileInputStream fis =
                new FileInputStream("./src/main/resources/commonData.properties");

        Properties p = new Properties();

        p.load(fis);

        return p.getProperty(data);

    }

}