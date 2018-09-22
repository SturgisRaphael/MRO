package mscp;

import org.json.*;
import util.JSONUtils;

public class Main {

    public static void main(String[] args) {
        String path = "/home/raphael/University/Master/Master2/MRO/Project/Frequencies-Affectations/data/celar_50_7_10_5_0.800000_0.json";
        JSONObject obj = JSONUtils.getJSONObjectFromFile(path);
        String[] names = JSONObject.getNames(obj);
        for(String string : names) {
            System.out.println(string + ": " + obj.get(string)+ ": " + obj.get(string).getClass());
        }

        Stations st = new Stations(obj);
        System.out.println(st.toString());
    }

}