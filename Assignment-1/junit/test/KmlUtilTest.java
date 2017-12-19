package test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import libraries.KmlUtil;

public class KmlUtilTest {

	@Test
	public void test() {
		double lon = 361;
		double lat = -91;
		int data1 = 9;
		int data2 = 12;
		double signal = -80;
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);

		assertEquals(false, KmlUtil.checkLongitude(lon));
		assertEquals(false, KmlUtil.checkLongitude(lat));
		assertEquals("09", KmlUtil.dataChange(data1));
		assertEquals("12", KmlUtil.dataChange(data2));
		assertEquals("2017-08-12T22:00:10Z", KmlUtil.timeInput(date));
		assertEquals("#ylw", KmlUtil.color(signal));

	}

}
