package test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p.SampleAlgo1;

public class SampleAlgo1Test {

	@Test
	public void test() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		SampleAlgo1 sa = new SampleAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(index, sa.getIndex());
		assertEquals(macName, sa.getMacName());
		assertEquals(ssid, sa.getSsid());
		assertEquals(frequency, sa.getFrequency());
		assertEquals(signal, sa.getSignal(),0.0001);
		assertEquals(localisation, sa.getLocalisation());
		assertEquals(date, sa.getDate());
	}

}
