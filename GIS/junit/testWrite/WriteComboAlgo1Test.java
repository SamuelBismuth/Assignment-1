package testWrite;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import algorithms.LineAlgo1;
import objects.Mac;
import objects.MacLocation;
import objects.MacLocationAlgo1;
import objects.Wifi;
import write.WriteComboAlgo1;

/**
 * @author Orel and Samuel.
 *
 */
public class WriteComboAlgo1Test {

	/**
	 * Test method for {@link write.WriteComboAlgo1#receiveData(java.util.ArrayList)}.
	 */
	@Test
	public void testReceiveData() {
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();
		ArrayList<MacLocation> arrayMacLocation = new ArrayList<MacLocation>();
		arrayMacLocation.add(
				new MacLocationAlgo1(
						new EarthCoordinate(
								32.0,
								32.0,
								100.0
								),
						-80,
						new Wifi (
								"wifiname",
								"mac",
								2400, 
								-80
								)
						)
				);
				Mac mac = new Mac(
						"mac",
						arrayMacLocation,
						new GregorianCalendar(
								12,
								10,
								2017
								)
						);
				mac.setLineAlgo1(
						new LineAlgo1(
								0,
								"mac",
								"ssid",
								2500,
								-40,
								new EarthCoordinate(
										32.0, 
										32.0, 
										0.0
										),
								new GregorianCalendar(
										2017,
										02,
										12
										)
								)
						);
				arrayMac.add(mac);
				String fileName = "TableMac";
				WriteComboAlgo1 write = new WriteComboAlgo1(fileName);
				write.receiveData(arrayMac); 	
	}

}
