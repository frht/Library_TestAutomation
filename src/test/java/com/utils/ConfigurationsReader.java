package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationsReader {


    private static Properties properties = new Properties();

    static {

        try {
            FileInputStream file = new FileInputStream("configurations.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("The file was not found");
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyWord){
        return properties.getProperty(keyWord);
    }





}
