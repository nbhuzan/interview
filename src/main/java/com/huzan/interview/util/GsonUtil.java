package com.huzan.interview.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by huzan on 16/6/4.
 */
public class GsonUtil {
    private static Gson gson=getGson();
    private GsonUtil(){}

    private static Gson getGson(){
        if(gson==null){
            gson=new Gson();
        }
        return gson;
    }

    public static String toJson(Object obj){
        String json=gson.toJson(obj);
        try {
            return URLEncoder.encode(json, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJsonNoEncode(Object obj){
        String json=gson.toJson(obj);
        try {
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String json,Class<T> classoft){
        try {
            json= URLDecoder.decode(json, "utf-8");
            return gson.fromJson(json, classoft);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T fromJsonNoDecode(String json,Class<T> classoft){
        try {
//			json=URLDecoder.decode(json, "utf-8");
            return gson.fromJson(json, classoft);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T fromJsonNoDecode(String json, Type type){
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
