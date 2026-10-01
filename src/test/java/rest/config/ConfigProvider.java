package rest.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {

    String configPath = System.getenv("CONFIG_LOCATION");
    Properties props = new Properties();   // ← создаём объект
    public ConfigProvider(){
        try {
            props.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getProperty(String key){
        return props.getProperty(key);
    }


}
