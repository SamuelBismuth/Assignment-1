package runs;

import java.util.ArrayList;

import libraries.Algorithm2;
import objects.SampleScan;
import objects.WeigthAverage;

/**
 * This class implements {@link Runnable}.
 * The call method is use to set the coordinates of the algorithm2.
 *
 * @author Orel and Samuel.
 */
public class RunSetCoordinates implements Runnable {

    private SampleScan input;
    private ArrayList<WeigthAverage> arrayData;

    /**
     * Constructor.
     *
     * @param input
     * @param arrayData
     */
    public RunSetCoordinates(SampleScan input, ArrayList<WeigthAverage> arrayData) {
        this.input = input;
        this.arrayData = arrayData;
    }

    /**
     * This run method set the coordinate into the array.
     */
    @Override
    public void run() {
        Algorithm2.setArrayRelevantNumber(input, arrayData);
        double sum = 0;
        for (WeigthAverage scan : arrayData)
            sum += scan.getRelevantNumber();
        if (sum != 0) {
            Algorithm2.setArrayPi(input, arrayData);
            Algorithm2.setLocation(input, arrayData);
        }
    }

}
