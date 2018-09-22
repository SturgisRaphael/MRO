package struct;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Station {
    int id;
    int region;
    int delta;
    ArrayList<Integer> EmitingFrequencies = new ArrayList<>();
    ArrayList<Integer> ReceivingFrequencies = new ArrayList<>();

    public Station(JSONObject station) {
        JSONArray emitingArray = station.getJSONArray("emetteur");
        for(int i = 0; i < emitingArray.length(); i++)
        {
            EmitingFrequencies.add(emitingArray.getInt(i));
        }

        JSONArray receivingArray = station.getJSONArray("recepteur");
        for(int i = 0; i < receivingArray.length(); i++)
        {
            ReceivingFrequencies.add(receivingArray.getInt(i));
        }

        id = station.getInt("num");
        region = station.getInt("region");
        delta = station.getInt("delta");
    }

    public int getId() {
        return id;
    }

    public int getRegion() {
        return region;
    }

    public int getDelta() {
        return delta;
    }

    public ArrayList<Integer> getEmitingFrequencies() {
        return EmitingFrequencies;
    }

    public ArrayList<Integer> getReceivingFrequencies() {
        return ReceivingFrequencies;
    }

    @Override
    public String toString() {
        return "Station{" +
                "\n\tid=" + id +
                "\n\t region=" + region +
                "\n\t delta=" + delta +
                "\n\t EmitingFrequencies=" + EmitingFrequencies +
                "\n\t ReceivingFrequencies=" + ReceivingFrequencies +
                "\n\t}";
    }
}
