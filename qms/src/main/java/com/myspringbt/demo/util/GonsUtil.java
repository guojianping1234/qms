package com.myspringbt.demo.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GonsUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static String toJSON(Object object) {
        return gson.toJson(object);

    }

    public static Object fromJson(String json, Class<?> cc) {
        return gson.fromJson(json, cc);
    }


}
