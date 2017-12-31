package cast;

import java.util.ArrayList;

import objects.LineAlgo1;
import objects.Mac;

/**
 * This class implements @see {@link Cast}.
 * This class cast the {@link ArrayList} of {@link Mac} to an {@link ArrayList} of {@link LineAlgo1}.
 * @author Orel and Samuel.
 */
public class CastFromMacToLineAlgo1 extends Cast<Mac, LineAlgo1> {

	/**
	 * Empty constructor.
	 */
	public CastFromMacToLineAlgo1() {}

	/**
	 * This method create an {@link ArrayList} of {@link LineAlgo1} from an ArrayList of {@link Mac}.
	 * In the object {@link LineAlgo1} is found all the information needed to create the new coordinates.
	 * @param arrayMac.
	 * @return {@link LineAlgo1}.
	 */
	@Override
	public ArrayList<LineAlgo1> cast(ArrayList<Mac> arrayMac)  {
		ArrayList<LineAlgo1> arrayLineAlgo1 = new ArrayList<LineAlgo1>();
		int index = 0;
		for (Mac mac : arrayMac) 
			arrayLineAlgo1.add(newLineAlgo1(mac, index++));
		return arrayLineAlgo1;
	}

	//Private unimplemented method.

	/**
	 * Create a new {@link LineAlgo1}.
	 * @param mac.
	 * @param index.
	 * @return {@link LineAlgo1}.
	 */
	private LineAlgo1 newLineAlgo1(Mac mac, int index) {
		return new LineAlgo1(
				index++,
				mac.getMacName(),
				mac.getArrayMacLocation().get(0).getWifi().getName(),
				mac.getArrayMacLocation().get(0).getWifi().getFrequency(), 
				mac.getNumberOfMac(),
				mac.getArrayMacLocation().get(0).getWifi().getSignal(),
				mac.getWeightCenter(),
				mac.getDate()
				);
	}

}
