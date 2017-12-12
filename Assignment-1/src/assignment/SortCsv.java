package assignment;

import java.util.ArrayList;

/**
 * This abstract class defines only for methods : sortBy, needToCreateObject, addMotherObject, addObject.
 * Then, two classes implements this interface : @see {@link SortCsvMac}, @see {@link SortCsvTime}.
 * This method extends @see {@link Date}.
 * @author Orel and Samuel
 */
public interface SortCsv<T> {

	public abstract ArrayList<T> sortBy(ArrayList<CsvFile> arrayCsv);
	public abstract boolean needToCreateObject(String str, Object object);
	public abstract Object addMotherObject(Line line);
	public abstract Object addObject(Line line);

}
