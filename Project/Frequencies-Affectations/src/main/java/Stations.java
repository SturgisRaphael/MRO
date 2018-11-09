import org.xcsp.common.IVar;
import org.xcsp.modeler.api.ProblemAPI;

import java.util.ArrayList;


public class Stations implements ProblemAPI {
    Station[] stations;
    int[] regions;
    Interference[] interferences;
    Liaison[] liaisons;

    public class Station{
        int num;
        int region;
        int delta;
        int[] emetteur;
        int[] recepteur;
    }

    public class Interference {
        int x;
        int y;
        int Delta;
    }

    public class Liaison {
        int x;
        int y;
    }

    @Override
    public void model() {
        int nStations = stations.length;
        int nInterference = interferences.length;
        int nLiaison = liaisons.length;
        int nRegions = regions.length;

        IVar.Var[] xe = new IVar.Var[stations.length];
        IVar.Var[] xr = new IVar.Var[stations.length];
        for(int i = 0; i < stations.length; i++){
            xe[i] = var("xe_"+i, dom(stations[i].emetteur));
            xr[i] = var("xr_"+i, dom(stations[i].recepteur));
        }

        forall(range(nStations), i -> lessThan(stations[i].delta, dist(xe[i], xr[i]))).note("A station must have delta between emitting and receiving frequencies");

        forall(range(nInterference), i -> lessThan(interferences[i].Delta, dist(xe[interferences[i].x], xe[interferences[i].y]))).note("If interferences between two stations then must frequencies must be Delta apart...(emitting, emitting)");
        forall(range(nInterference), i -> lessThan(interferences[i].Delta, dist(xe[interferences[i].x], xr[interferences[i].y]))).note("If interferences between two stations then must frequencies must be Delta apart...(emitting, receiving)");
        forall(range(nInterference), i -> lessThan(interferences[i].Delta, dist(xr[interferences[i].x], xe[interferences[i].y]))).note("If interferences between two stations then must frequencies must be Delta apart...(receiving, emitting)");
        forall(range(nInterference), i -> lessThan(interferences[i].Delta, dist(xr[interferences[i].x], xr[interferences[i].y]))).note("If interferences between two stations then must frequencies must be Delta apart...(receiving, receiving)");

        forall(range(nLiaison), i -> equal(xe[liaisons[i].x], xr[liaisons[i].y])).note("If stations linked then they must have the same emitting and receiving Frequency");
        forall(range(nLiaison), i -> equal(xr[liaisons[i].x], xe[liaisons[i].y])).note("If stations linked then they must have the same receiving and emitting Frequency");

        //Limit on number of frequencies per region
        for(int i = 0; i < nRegions; i++)
        {
            ArrayList<IVar.Var> tmpRegionFrequenciesArray = new ArrayList<IVar.Var>();

            for(int j = 0; j < nStations; j++)
            {
                if(stations[j].region == i)
                {
                    tmpRegionFrequenciesArray.add(xe[j]);
                    tmpRegionFrequenciesArray.add(xr[j]);
                }
            }

            IVar.Var[] tmpRegionFrequencies = new IVar.Var[tmpRegionFrequenciesArray.size()];

            for(int j = 0; j < tmpRegionFrequenciesArray.size(); j++)
            {
                tmpRegionFrequencies[j] = tmpRegionFrequenciesArray.get(j);
            }

            nValues(tmpRegionFrequencies, LE, regions[i]).note("Limit on number of frequencies per region");
        }

        IVar.Var[] allFrequencies = new IVar.Var[nStations * 2];
        for(int i = 0; i < nStations; i++)
        {
            allFrequencies[2*i] = xe[i];
            allFrequencies[2*i+1] = xr[i];
        }

        if(modelVariant("COP1"))
        {
            IVar.Var nFrequencies = var("nFrequencies", dom(range(nStations * 2)));

            nValues(allFrequencies, EQ, nFrequencies);

            minimize(nFrequencies);
        }

        if(modelVariant("COP2"))
            minimize(MAXIMUM, allFrequencies);

        if(modelVariant("COP3"))
        {
            IVar.Var maxFrequency = var("maxFrequency", dom(range(1000)));
            IVar.Var minFrequency = var("minFrequency", dom(range(stations[0].emetteur[0] +1 )));
            IVar.Var spanFrequency = var("spanFrequency", dom(range(1000)));

            maximum(allFrequencies, maxFrequency);
            minimum(allFrequencies, minFrequency);
            equal(spanFrequency,dist(minFrequency, maxFrequency));

            minimize(spanFrequency);
        }
    }
}
