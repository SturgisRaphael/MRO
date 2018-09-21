package mscp;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Tuple;
import org.xcsp.modeler.api.ProblemAPI;

import java.util.ArrayList;
import java.util.Map;

public abstract class Stations implements ProblemAPI {
    ArrayList<Station> stations;
    Map<Integer, Integer> regions;
    ArrayList<Triplet<Integer, Integer, Integer>> interferences;
    ArrayList<Pair<Integer, Integer>> links;


}
