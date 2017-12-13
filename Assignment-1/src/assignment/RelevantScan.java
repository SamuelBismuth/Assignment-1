package assignment;

import java.util.ArrayList;

public class RelevantScan {

	private final static int NBOFMAC = 4;

	public static double getRelevantNumber(Scan data, Scan input) {
		if (data.getWifiNetworks() > NBOFMAC || input.getWifiNetworks() > NBOFMAC) 
			return revelantNumber(data.getArrayStrongerWifiConstantNumber(NBOFMAC), input.getArrayStrongerWifiConstantNumber(NBOFMAC), 0, 0);
		else 
			return revelantNumber(data.getArrayStrongerWifi(), input.getArrayStrongerWifi(), 0, 0);
	}

	private static double revelantNumber(ArrayList<Wifi> data, ArrayList<Wifi> input, int i, double number) {
		System.out.println(number);
		if (i < data.size() && i < input.size()) {
			number +=
					((Math.abs(input.get(i).getSignal())
							- Math.abs(data.get(i).getSignal() - input.get(i).getSignal()))
							/ Math.abs(input.get(i).getSignal()) );
			return revelantNumber(data, input, ++i, number);
		}
		else if (data.size() > input.size()) return number *  Math.pow(0.1, Math.abs(data.size() - input.size()));
		else return number;
	}


}
