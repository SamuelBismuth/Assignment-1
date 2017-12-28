package runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import cast.Cast;

public class CallableCast<T, Y> implements Callable<ArrayList<Y>> {

	private Cast<T, Y> cast;		
	private ArrayList<T> array;

	public CallableCast(Cast<T, Y> cast, ArrayList<T> array) {
		this.cast = cast;
		this.array = array;
	}

	@Override
	public ArrayList<Y> call() throws Exception {
		System.out.println("Beginning the" + cast.toString());
		return cast.cast(array);
	}

}
