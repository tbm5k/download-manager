package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesExtract {

    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String appConfigPath = rootPath + "app.properties";
    Properties appProperties = new Properties();

    public String appName() throws IOException {
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("name");
    }

    public String downloadsPath() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("downloadsPath");
    }

    public String imgDestination() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("imageDes");
    }

    public String videoDestination() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("videosDes");
    }

    public String zippedDestination() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("zippedDes");
    }

    public String docsDestination() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("docsDes");
    }

    public String undefinedDestination() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("undefDes");
    }

    public String musicDestination() throws IOException{
        appProperties.load(new FileInputStream(appConfigPath));
        return appProperties.getProperty("musicDes");
    }
}
