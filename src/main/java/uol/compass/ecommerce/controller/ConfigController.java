package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.config.Config_Properties;
import uol.compass.ecommerce.model.config.Locales;
import uol.compass.ecommerce.model.config.Messages;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class ConfigController {
    private static final String CONFIG_NAME = "config.properties";
    private File appConfig;

    public ConfigController() {
        this.appConfig = new File("./" + CONFIG_NAME);
    }

    public boolean createConfig() {
        boolean isFileCreated = appConfig.exists();
        if (!appConfig.exists()) {
            try {
                isFileCreated = appConfig.createNewFile();
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.CONFIGURATION_CREATE_ERROR) + e.getMessage());
            }
            try (Writer inputStream = new FileWriter(appConfig); FileInputStream propsInput = new FileInputStream(CONFIG_NAME)) {

                Properties prop = new Properties();

                prop.load(propsInput);

                // Setting the properties.
                prop.setProperty("MYSQL_HOST", "127.0.0.1");
                prop.setProperty("MYSQL_PORT", "3306");
                prop.setProperty("MYSQL_USER", "root");
                prop.setProperty("MYSQL_PASSWORD", "");
                prop.setProperty("MYSQL_DATABASE", "ecommerce");
                prop.setProperty("APP_LOCALE", "en");
                // Storing the properties in the file with a heading comment.
                prop.store(inputStream, "Config created at " + new Date());


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return isFileCreated;
    }

    public String getProperty(Config_Properties property) {
        Properties prop = new Properties();
        try (FileInputStream propsInput = new FileInputStream(CONFIG_NAME)) {
            prop.load(propsInput);
        } catch (IOException ex) {
            System.err.println(Main.getMessage(Messages.GET_PROPERTY_ERROR) + "\n" + ex.getMessage());
        }
        return prop.getProperty(property.toString());
    }

    public void setHost(String host) {
        if (appConfig.exists()) {
            try (FileInputStream in = new FileInputStream(CONFIG_NAME)) {
                Properties props = new Properties();
                props.load(in);
                props.setProperty("MYSQL_HOST", host);

                try (FileOutputStream out = new FileOutputStream(CONFIG_NAME)) {
                    props.store(out, "Latest Update: " + new Date());
                    out.flush();
                    Main.loadConfig();
                } catch (IOException e) {
                    System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
            }

        } else {
            createConfig();
        }
    }

    public void setPort(String port) {
        if (appConfig.exists()) {
            try (FileInputStream in = new FileInputStream(CONFIG_NAME)) {
                Properties props = new Properties();
                props.load(in);
                props.setProperty("MYSQL_PORT", port);

                try (FileOutputStream out = new FileOutputStream(CONFIG_NAME)) {
                    props.store(out, "Latest Update: " + new Date());
                    out.flush();
                    Main.loadConfig();
                } catch (IOException e) {
                    System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
            }
        } else {
            createConfig();
        }
    }


    public void setDatabase(String database) {
        if (appConfig.exists()) {
            try (FileInputStream in = new FileInputStream(CONFIG_NAME)) {
                Properties props = new Properties();
                props.load(in);
                props.setProperty("MYSQL_DATABASE", database);

                try (FileOutputStream out = new FileOutputStream(CONFIG_NAME)) {
                    props.store(out, "Latest Update: " + new Date());
                    out.flush();
                    Main.loadConfig();
                } catch (IOException e) {
                    System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
            }
        } else {
            createConfig();
        }
    }

    public void setUser(String user) {
        if (appConfig.exists()) {
            try (FileInputStream in = new FileInputStream(CONFIG_NAME)) {
                Properties props = new Properties();
                props.load(in);
                props.setProperty("MYSQL_USER", user);

                try (FileOutputStream out = new FileOutputStream(CONFIG_NAME)) {
                    props.store(out, "Latest Update: " + new Date());
                    out.flush();
                    Main.loadConfig();
                } catch (IOException e) {
                    System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
            }
        } else {
            createConfig();
        }
    }

    public void setLocale(Locales locale) {
        if (appConfig.exists()) {
            try (FileInputStream in = new FileInputStream(CONFIG_NAME)) {
                Properties props = new Properties();
                props.load(in);
                props.setProperty("APP_LOCALE", locale.toString());

                try (FileOutputStream out = new FileOutputStream(CONFIG_NAME)) {
                    props.store(out, "Latest Update: " + new Date());
                    out.flush();
                    Main.loadConfig();
                } catch (IOException e) {
                    System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
            }
        } else {
            createConfig();
        }
    }


    public void setPassword(String password) {
        if (appConfig.exists()) {
            try (FileInputStream in = new FileInputStream(CONFIG_NAME)) {
                Properties props = new Properties();
                props.load(in);
                props.setProperty("MYSQL_PASSWORD", password);

                try (FileOutputStream out = new FileOutputStream(CONFIG_NAME)) {
                    props.store(out, "Latest Update: " + new Date());
                    out.flush();
                    Main.loadConfig();
                } catch (IOException e) {
                    System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(Main.getMessage(Messages.SET_PROPERTY_ERROR) + "\n" + e.getMessage());
            }
        } else {
            createConfig();
        }
    }


}
