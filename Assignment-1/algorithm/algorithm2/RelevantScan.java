package algorithm2;

import read.SampleScan;
import read.Wifi;

public class RelevantScan {

	private final static int NBOFMAC = 4;

	public static double getRelevantNumber(SampleScan ScanData, SampleScan ScanInput) {
		Wifi data = null;
		double number = 1;
		for (Wifi input : ScanInput.getArrayStrongerWifiConstantNumber(NBOFMAC)) {
			data = ScanData.containsSameMac(input.getMac());
			number *= revelantNumber(data, input);
		}
		if (number >= 1) return 0;
		return number;
	}

	private static double revelantNumber(Wifi data, Wifi input) {
		if (data == null) {
			input.setSignal(-120);
			return 0.1;
		}
		return 
				((Math.abs(input.getSignal())
						- Math.abs(data.getSignal() - input.getSignal()))
						/ Math.abs(input.getSignal()) );		
	}

}
