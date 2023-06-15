package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class ConfigController {
    private static final String CONFIG_NAME = "config.properties";
    private File appConfig;

    public ConfigController(){
        this.appConfig = new File("./" + CONFIG_NAME);
    }

    public boolean createConfig() throws IOException {
        boolean isFileCreated = appConfig.exists();
        if (!appConfig.exists()) {
            isFileCreated = appConfig.createNewFile();
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
                prop.store(inputStream, "Config created at " + new Date());


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return isFileCreated;
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
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                createConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                createConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                createConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                createConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                createConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
