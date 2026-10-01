package rest.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TaskConfig {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = TaskConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties не найден в classpath");
            }
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения config.properties", e);
        }
    }

    public static String getUrl() {
        return PROPS.getProperty("URL");
    }

    public static int getTimeout() {
        return Integer.parseInt(PROPS.getProperty("TIMEOUT", "10"));
    }

    public static String getLogMode() {
        return PROPS.getProperty("LOG_MODE", "ON");
    }

    public static String getAdminLogin() {
        return PROPS.getProperty("ADMIN_LOGIN");
    }

    public static String getAdminPassword() {
        return PROPS.getProperty("ADMIN_PASSWORD");
    }

    public static String getBaseGoodName() {
        return PROPS.getProperty("BASE_GOOD_NAME");
    }

    public static String getBaseGoodPrice() {
        return PROPS.getProperty("BASE_GOOD_PRICE");
    }


}
