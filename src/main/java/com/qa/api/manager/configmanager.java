package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configmanager {


    public static Properties properties = new Properties();

    static {
        InputStream input = configmanager.class.getClassLoader().getResourceAsStream("config/config.properties");

        if(input!=null)
        {
            try {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static String get(String key)
    {
        return properties.getProperty(key);
    }

    public static void set(String key,String value)
    {
        properties.setProperty(key,value);
    }


}
