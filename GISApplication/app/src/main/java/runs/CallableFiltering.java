package runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import filter.Filtering;

/**
 * This class implements {@link Callable}.
 * The call method is use for the filtering.
 *
 * @author Orel and Samuel.
 */
public class CallableFiltering<T> implements Callable<ArrayList<T>> {

    private Filtering<T> filter;
    private ArrayList<T> array;

    /**
     * Constructor.
     *
     * @param filter
     * @param array
     */
    public CallableFiltering(Filtering<T> filter, ArrayList<T> array) {
        this.filter = filter;
        this.array = array;
    }

    /**
     * This call method run the filter.
     *
     * @return ArrayList of the parameter.
     * @throws Exception
     */
    @Override
    public ArrayList<T> call() throws Exception {
        return filter.filteringBy(array);
    }

}
