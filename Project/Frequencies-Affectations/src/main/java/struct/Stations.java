package struct;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Stations{
    ArrayList<Station> stations = new ArrayList<>();
    ArrayList<Pair<Integer, Integer>> regions = new ArrayList<>();
    ArrayList<Triplet<Integer, Integer, Integer>> interferences = new ArrayList<>();
    ArrayList<Pair<Integer, Integer>> links = new ArrayList<>();

    public Stations(JSONObject obj) {

        JSONArray regionsArray = obj.getJSONArray("regions");

        for(int i = 0; i < regionsArray.length(); i++)
        {
            //System.out.println(regionsArray.get(i-1));
            regions.add(new Pair<>(i + 1, regionsArray.getInt(i)));
        }

        JSONArray linksArray = obj.getJSONArray("liaisons");

        for(int i = 0; i < linksArray.length(); i++){
            //System.out.println(linksArray.get(i) + " : " + linksArray.get(i).getClass());
            JSONObject elm = linksArray.getJSONObject(i);
            links.add(new Pair<>(elm.getInt("x"), elm.getInt("y")));
        }

        JSONArray stationsArray = obj.getJSONArray("stations");
        for (int i = 0; i < stationsArray.length(); i++) {
            JSONObject station = stationsArray.getJSONObject(i);
            //System.out.println(station);
            stations.add(new Station(station));
        }

        JSONArray interferencesArray = obj.getJSONArray("interferences");
        for(int i = 0; i < interferencesArray.length(); i++){
            //System.out.println(linksArray.get(i) + " : " + linksArray.get(i).getClass());
            JSONObject elm = interferencesArray.getJSONObject(i);
            interferences.add(new Triplet<Integer, Integer, Integer>(elm.getInt("x"), elm.getInt("y"), elm.getInt("Delta")));
        }
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Pair<Integer, Integer>> getRegions() {
        return regions;
    }

    public ArrayList<Triplet<Integer, Integer, Integer>> getInterferences() {
        return interferences;
    }

    public ArrayList<Pair<Integer, Integer>> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "Stations{" +
                "\nstations=" + stations +
                "\nregions=" + regions +
                "\ninterferences=" + interferences +
                "\nlinks=" + links +
                "\n}";
    }
}
