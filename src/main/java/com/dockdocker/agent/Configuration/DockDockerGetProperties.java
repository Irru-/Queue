package com.dockdocker.agent.Configuration;

import java.io.*;
import java.util.Properties;

/**
 * Created by lars on 16-5-16.
 */
public class DockDockerGetProperties {

    public String getProperty(String value) throws IOException {

        String result = "";

        try {
            File file = new File("src/main/resources/config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fileInput);

            result = prop.getProperty(value);
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
