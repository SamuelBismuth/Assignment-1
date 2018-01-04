package runs;

import java.util.ArrayList;

import objects.SampleScan;
import write.WriteFile;

/**
 * This class implements {@link Runnable}.
 * The call method is use for writing a file.
 *
 * @param <T>
 * @author Orel and Samuel.
 */
public class RunWrite<T> implements Runnable {

    private WriteFile write;
    private ArrayList<T> array;

    /**
     * Constructor.
     *
     * @param write
     * @param array
     */
    public RunWrite(WriteFile write, ArrayList<T> array) {
        this.write = write;
        this.array = array;
    }

    /**
     * This run method write a file.
     */
    @Override
    public void run() {
        write.receiveData(array);
    }

}
