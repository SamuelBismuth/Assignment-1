package userChoice;

import filter.Filtering;
import libraries.InputException;
import read.SampleScan;

/**
 * @author Samuel
 *
 */
public interface UserChoice {

	public Filtering<SampleScan> choice() throws InputException;
	
}
