package runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import cast.Cast;

public class CallableCast<F, T> implements Callable<ArrayList<T>> {

	private Cast<F, T> cast;
	private ArrayList<F> array;

	public CallableCast(Cast<F, T> cast, ArrayList<F> array) {
		this.cast = cast;
		this.array = array;
	}

	@Override
	public ArrayList<T> call() throws Exception {
		System.out.println("Beginning the" + cast.toString());
		return cast.cast(array);
	}

}
