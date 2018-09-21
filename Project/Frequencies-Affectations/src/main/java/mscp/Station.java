package mscp;

import java.util.ArrayList;

public class Station {
    int id;
    int region;
    int delta;
    ArrayList<Integer> EmitingFrequencies;
    ArrayList<Integer> ReceivingFrequencies;

    public Station(int id, int region, int delta, ArrayList<Integer> emitingFrequencies, ArrayList<Integer> receivingFrequencies) {
        this.id = id;
        this.region = region;
        this.delta = delta;
        EmitingFrequencies = emitingFrequencies;
        ReceivingFrequencies = receivingFrequencies;
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
}
