package userChoice;

import filter.Filtering;
import libraries.InputException;
import objects.SampleScan;

/**
 * @author Samuel
 *
 */
public interface UserChoice {

	public Filtering<SampleScan> choice() throws InputException;
	
}
