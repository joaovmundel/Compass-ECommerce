package uol.compass.ecommerce.controller;

import java.io.*;
import java.util.Properties;

public class ConfigController {
    private static final String CONFIG_NAME = "config.properties";
    public void createConfig() throws IOException {
        File appConfig = new File("./" + CONFIG_NAME);
        if (!appConfig.exists()) {
            appConfig.createNewFile();
            try (Writer inputStream = new FileWriter(appConfig); FileInputStream propsInput = new FileInputStream(CONFIG_NAME)) {

                Properties prop = new Properties();

                prop.load(propsInput);

                // Setting the properties.
                prop.setProperty("MYSQL_HOST", "127.0.0.1");
                prop.setProperty("MYSQL_PORT", "3306");
                prop.setProperty("MYSQL_USER", "root");
                prop.setProperty("MYSQL_PASSWORD", "fatec123*");
                prop.setProperty("MYSQL_DATABASE", "ecommerce");

                // Storing the properties in the file with a heading comment.
                prop.store(inputStream, "MYSQL CONFIGURATION");


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        Properties prop = new Properties();
        try (FileInputStream propsInput = new FileInputStream(CONFIG_NAME)) {
            prop.load(propsInput);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
