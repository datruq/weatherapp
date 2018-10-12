package com.finleap.casestudy.weatherapp.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String readResource(String fileName) {

        try {
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            return "";
        }
    }
}
