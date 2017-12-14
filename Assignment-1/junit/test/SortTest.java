package test;

//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//
//import org.boehn.kmlframework.coordinates.EarthCoordinate;
//import org.junit.Test;
//
//import assignment1.Sort;
//import assignment1.Wifi;

public class SortTest {

/**
 * Here we have tested all the private method, but, we need them to be private, and so, junit test can't read private method.
 * If you need to run those tests, you need to change from private to public all the method in the Sort class.
 */
//	@Test
//	public void testSort() { // Constructor test.
//		ArrayList<Wifi> array = new ArrayList<Wifi>();
//		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
//		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
//		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -70);
//		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
//		Wifi wifi2 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -90);
//		Wifi wifi3 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -80);
//		array.add(wifi);
//		array.add(wifi2);
//		array.add(wifi3);
//		new Sort(array);
//		assertEquals(-90, array.get(0).getSignal());
//		assertEquals(-80, array.get(1).getSignal());
//	}
//	
//	@Test
//	public void testSortByTime() {
//		ArrayList<Wifi> array = new ArrayList<Wifi>();
//		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
//		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
//		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -70);
//		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
//		Wifi wifi2 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -90);
//		Wifi wifi3 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -80);
//		array.add(wifi);
//		array.add(wifi2);
//		array.add(wifi3);
//		Sort sort = new Sort();
//		sort.sortByTime(array);
//		assertEquals(-90, array.get(0).getSignal());
//		assertEquals(-80, array.get(1).getSignal());
//	}
//	
//	@Test
//	public void testSortByRSSI() {
//		ArrayList<Wifi> array = new ArrayList<Wifi>();
//		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
//		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
//		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -70);
//		Wifi wifi2 = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -90);
//		array.add(wifi);
//		array.add(wifi2);
//		Sort sort = new Sort();
//		sort.sortByRSSI(array, 0, 0);
//		assertEquals(-90, array.get(0).getSignal());
//	}
//	
//	@Test
//	public void testMyComparator() {
//		Sort sort = new Sort();
//		assertEquals(false, sort.myComparator(-89, 67));
//		assertEquals(true, sort.myComparator(67, -89));
//
//	}

}
