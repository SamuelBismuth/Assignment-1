package runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import cast.Cast;

/**
 * This class implements {@link Callable}.
 * The call method is use for the cast.
 *
 * @param <F>
 * @param <T>
 *
 * @author Orel and Samuel.
 */
public class CallableCast<F, T> implements Callable<ArrayList<T>> {

    private Cast<F, T> cast;
    private ArrayList<F> array;

    /**
     * {@link java.lang.reflect.Constructor}.
     * @param cast
     * @param array
     */
    public CallableCast(Cast<F, T> cast, ArrayList<F> array) {
        this.cast = cast;
        this.array = array;
    }

    @Override
    public ArrayList<T> call() throws Exception {
        return cast.cast(array);
    }

}
