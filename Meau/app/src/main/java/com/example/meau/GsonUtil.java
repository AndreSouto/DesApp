package com.example.meau;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonUtil {
    public static <T> String serialize(T obj) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
            Gson serializer = builder.create();
            if (obj != null) {
                return serializer.toJson(obj);
            }
        } catch (Exception ex) {

        }
        return "";
    }

    public static Object deserialize(Type type, String json) {
        try {
            if (json.indexOf("\"") == 0) {
                json = json.replace("\\", "");
                return json.substring(1, json.length()-1);
            } else {
                GsonBuilder builder = new GsonBuilder();
                builder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
                Gson serializer = builder.create();
                if (json != null && !json.equals("")) {
                    return serializer.fromJson(json, type);
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }
}
