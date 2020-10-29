package com.library.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {


    private static Properties properties = new Properties();

    static {
        try (FileInputStream file = new FileInputStream("configuration.properties")){
            properties.load(file);
        } catch (Exception e) {
            System.out.println("The file was not found");
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration.properties file");
        }

    }

    public static String getProperty(String keyWord){
        return properties.getProperty(keyWord);
    }





}
