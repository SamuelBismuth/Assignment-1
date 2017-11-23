package test;

//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import assignment1.InputException;
//import assignment1.Verification;

public class VerificationTest {

/**
 * Here we have tested all the protected method, but, we need them to be private, and so, junit test can't read private method.
 * If you need to run those tests, you need to change from private to public all the method in the Verification class.
 */
//	@Test
//	public void testCheckAltitude() {
//		Verification ver = new Verification();
//		assertEquals(true, ver.checkAltitude(98));
//		assertEquals(false, ver.checkAltitude(-5678));
//	}
//
//	@Test
//	public void testCheckLatitude() {
//		Verification ver = new Verification();
//		assertEquals(true, ver.checkLatitude(80.0));
//		assertEquals(false, ver.checkLatitude(900));
//	}
//
//	@Test
//	public void testCheckLongitude() {
//		Verification ver = new Verification();
//		assertEquals(true, ver.checkLongitude(200));
//		assertEquals(false, ver.checkLongitude(-9));
//	}
//
//	@Test
//	public void testCheckMac() {
//		Verification ver = new Verification();
//		assertEquals(true, ver.checkMac("6a:12:f5:f9:5e:71"));
//		assertEquals(false, ver.checkMac("mac"));	
//	}
//
//	@Test
//	public void testNoName() {
//		Verification ver = new Verification();
//		assertEquals("No name", ver.noName(""));
//		assertEquals("Wifi", ver.noName("Wifi"));
//	}
//
//	@Test
//	public void testCheckTheFile() {
//		Verification ver = new Verification();
//		assertEquals(true, ver.checkTheFile("WigleWifiblablablablabladisplay"));
//		assertEquals(false, ver.checkTheFile("igllewifiblablabla"));
//	}
//
//	@Test (expected = InputException.class)
//	public void testStringToDate() throws InputException {
//		String wrongDate = "2017-13-09 10:09:30";
//		String wrongDate2 = "I am a wrong date";
//		Verification ver = new Verification();
//		ver.stringToDate(wrongDate);
//		ver.stringToDate(wrongDate2);
//	}

}
