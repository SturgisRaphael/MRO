package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONObject;

public class JSONUtils {

    public static String getJSONStringFromFile(String path) {
        Scanner scanner;
        String json = null;
        try {
            scanner = new Scanner(new File(path));
            json = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONObject getJSONObjectFromFile(String path) {
        return new JSONObject(getJSONStringFromFile(path));
    }

    public static boolean objectExists(JSONObject jsonObject, String key) {
        Object o;
        try {
            o = jsonObject.get(key);
        } catch(Exception e) {
            return false;
        }
        return o != null;
    }

}