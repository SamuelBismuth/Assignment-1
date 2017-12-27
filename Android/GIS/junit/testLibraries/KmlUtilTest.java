package testLibraries;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import libraries.KmlUtil;

public class KmlUtilTest {

	/**
	 * This method check that the longitude value is in the right range
	 */
	@Test
	public void checkLongitudeTest() {
		double lon = 361;
		assertEquals(false, KmlUtil.checkLongitude(lon));
	}
	
	/**
	 * This method check that the latitude value is in the right range
	 */
	@Test
	public void checkLatitudeTest() {
		double lat = -91;
		assertEquals(false, KmlUtil.checkLatitude(lat));
	}
	
	/**
	 * This method check that the data is set in by right conditions
	 */
	@Test
	public void checkDataTest() {
		int data1 = 9;
		int data2 = 12;
		assertEquals("09", KmlUtil.dataChange(data1));
		assertEquals("12", KmlUtil.dataChange(data2));
	}
	
	/**
	 * This method check that the date was put by the timeInput conditions
	 */
	@Test
	public void timeInputTest() {
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		assertEquals("2017-08-12T22:00:10Z", KmlUtil.timeInput(date));
	}
	
	/**
	 * This method check that the color is set by the function's conditions
	 */
	@Test
	public void colorTest() {
		double signal = -80;
		assertEquals("#ylw", KmlUtil.color(signal));
	}

}
