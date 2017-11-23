package assignment1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * For each period of time, this class sorts the wifi by the signal.
 * @author Orel and Samuel.
 */

public class Sort {
	
	/**
	 * Constructor.
	 * @param array.
	 */
	public Sort(ArrayList<Wifi> array) {
		sortByTime(array);
		new WriteCsv(array);
	}

	/**
	 * The method sorts, for each lines of with the same time, by the signal.
	 * Two solutions :
	 * The first is to not worried, and to sort all the array. (solution we use)
	 * The second, if the running time is longer, we can add a if and ask if there is more than 10 wifi, if yes, sort, else, don't sort.
	 * @param array.
	 */
	private void sortByTime(ArrayList<Wifi> array) {
		for (int i = 0; i < array.size() - 1; i++) {
			int begining = i;
			while (i < array.size() - 1 && array.get(i).getTime().equals(array.get(i + 1).getTime())) i++;
			sortByRSSI(array, begining, i);
		}
	}

	/**
	 * The method use a bubble sort and swap if necessery.
	 * @see myComparator.
	 * @see swap.
	 * @param array.
	 * @param begining.
	 * @param end.
	 * @exception IndexOutOfBoundsException : //do nothing.
	 */
	private void sortByRSSI(ArrayList<Wifi> array, int begining, int end) {
		try {
		for (int i = begining; i <= end; i++) {
			for (int j = begining + 1; j <= begining + 1 + end - i; j++) { // bubble sort
				if (myComparator(array.get(j - 1).getSignal(), array.get(j).getSignal())) Collections.swap(array, j - 1, j);
			}
		}
		}
		catch (IndexOutOfBoundsException ex) {
			//do nothing
		}
	}

	/**
	 * Comparator method by the stronger signal.
	 * @param signal1.
	 * @param signal2.
	 * @return true if the first signal is more powerfull.
	 * @return false if the second signal is more powerfull.
	 */
	private boolean myComparator(int signal1, int signal2){
		if (signal1 > signal2) return true;
		else return false;
	}

}
