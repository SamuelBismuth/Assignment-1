package objects;

import com.gis.gisapplication.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import filter.Filtering;
import libraries.DataBase;
import runs.CallableFiltering;

/**
 * This class represents the object filter.
 *
 * @author Orel and Samuel.
 */
public class Filter implements Serializable {

    private ArrayList<SampleScan> array;
    private Logic logic;
    private boolean not;
    private Filtering filter1;
    private Filtering filter2;

    /**
     * Constructor.
     *
     * @param array
     * @param logic
     * @param not
     * @param filter1
     * @param filter2
     */
    public Filter(ArrayList<SampleScan> array, Logic logic, boolean not, Filtering filter1, Filtering filter2) {
        this.array = array;
        this.logic = logic;
        this.not = not;
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    /**
     * This method run the filter reflexion.
     *
     * @return
     */
    public ArrayList<SampleScan> run() {
        ArrayList<SampleScan> arrayFilter1 = new ArrayList<SampleScan>();
        ArrayList<SampleScan> arrayFilter2 = new ArrayList<SampleScan>();
        ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
        Future<ArrayList<SampleScan>> futureArrayFilter1 = null;
        Future<ArrayList<SampleScan>> futureArrayFilter2 = null;
        if (filter1 != null)
            futureArrayFilter1 = execut.submit(new CallableFiltering<SampleScan>(filter1, (ArrayList<SampleScan>) array.clone()));
        if (filter2 != null)
            futureArrayFilter2 = execut.submit(new CallableFiltering<SampleScan>(filter2, (ArrayList<SampleScan>) array.clone()));
        if (filter2 != null)
            while (!futureArrayFilter1.isDone() && !futureArrayFilter2.isDone()) ;
        else
            while (!futureArrayFilter1.isDone()) ;
        try {
            arrayFilter1 = futureArrayFilter1.get();
            if (filter2 != null)
                arrayFilter2 = futureArrayFilter2.get();
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        switch (this.logic.getLogic()) {
            case "&&":
                arrayFilter1.retainAll(arrayFilter2);
                break;
            case "||":
                arrayFilter1.removeAll(arrayFilter2);
                arrayFilter1.addAll(arrayFilter2);
                break;
            case "none":
                break;
            default:
        }
        if (not) {
            ArrayList<SampleScan> arrayTemp = (ArrayList<SampleScan>) array.clone();
            arrayTemp.removeAll(arrayFilter1);
            arrayFilter1 = arrayTemp;
        }
        DataBase.setArraySampleScan(arrayFilter1);
        MainActivity.refreshDataBase();
        return arrayFilter1;
    }

    /**
     * toString to display in the show filter.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return logic.toString() +
                ", not =" + not +
                ", filter1 =" + filter1 +
                ", filter2 =" + filter2;
    }

    /**
     * @return array.
     */
    public ArrayList<SampleScan> getArray() {
        return array;
    }
}
