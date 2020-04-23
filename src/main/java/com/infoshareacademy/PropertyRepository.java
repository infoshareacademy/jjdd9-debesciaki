package com.infoshareacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyRepository {
    private static List<Properties> allProperties = new ArrayList<>();

    private  PropertyRepository() {
    }

    public static List<Properties> getAllProperties(List<Properties> listOfProperties) {
        return allProperties;
    }

    public static void setAllProperties(List<Properties> allProperties) {
        PropertyRepository.allProperties = allProperties;
    }
}
