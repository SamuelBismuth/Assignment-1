package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import algorithm1.LineAlgo1;
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
		ArrayList<LineAlgo1> arraySampleScan = new ArrayList<LineAlgo1>();
		arraySampleScan.add(
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
		String fileName = "TableMac";
		WriteComboAlgo1 write = new WriteComboAlgo1(fileName);
		write.receiveData(arraySampleScan); 	
	}

}
