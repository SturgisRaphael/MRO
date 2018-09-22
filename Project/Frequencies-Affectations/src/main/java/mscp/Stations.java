package mscp;

import org.xcsp.modeler.api.ProblemAPI;

public class Stations implements ProblemAPI {
    Station[] stations;
    int[] regions;
    Interference[] interferences;
    Liaison[] liaisons;

    private class Station{
        int num;
        int region;
        int delta;
        int[] emetteur;
        int[] recepteur;
    }

    private class Interference {
        int x;
        int y;
        int Delta;
    }

    private class Liaison {
        int x;
        int y;
    }

    @Override
    public void model() {
        int[] xe = array("xe", size(stations.length), dom()); //TODO: ASK teacher...
    }
}
