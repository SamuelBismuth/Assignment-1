package runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import libraries.DataBase;
import objects.SampleScan;
import objects.WeigthAverage;

/**
 * This class implements {@link Callable}.
 * The call method is use for the algorithm 2.
 *
 * @author Orel and Samuel.
 */
public class CallableAlgorithm2 implements Callable<ArrayList<SampleScan>> {

    private ArrayList<SampleScan> arrayInput;

    /**
     * Constructor.
     *
     * @param arrayInput
     */
    public CallableAlgorithm2(ArrayList<SampleScan> arrayInput) {
        this.arrayInput = arrayInput;
    }

    /**
     * This call method return an {@link ArrayList} of {@link SampleScan} with the new coordinates.
     *
     * @return ArrayList of {@link SampleScan}.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<SampleScan> call() throws Exception {
        for (SampleScan input : arrayInput) {
            Thread thread = new Thread(
                    new RunSetCoordinates(
                            input,
                            (ArrayList<WeigthAverage>) DataBase.getArrayWeightAverage().clone()
                    )
            );
            thread.start();
            thread.join();
        }
        return arrayInput;
    }

}
