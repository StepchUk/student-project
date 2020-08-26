package loc.stalex.studentorder.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_LIMIT = "db.limit";

    private static final Properties PROPERTIES = new Properties();

    public static String getProperty(String name) {
        if (PROPERTIES.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("dao.properties")) {
                PROPERTIES.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return PROPERTIES.getProperty(name);
    }
}
