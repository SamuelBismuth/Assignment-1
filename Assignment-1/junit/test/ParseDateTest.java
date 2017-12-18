/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import library.ParseDate;
import library.InputException;

/**
 * @author Samuel
 *
 */
public class ParseDateTest {

	/**
	 * Test method for {@link library.Date#stringToDate(java.lang.String)}.
	 */
	@Test
	public void testStringToDate() {
		try {
			String dateString = "2017-10-27 16:13:51";
			GregorianCalendar calendar = ParseDate.stringToDate(dateString);
			assertEquals("Fri Oct 27 16:13:51 IDT 2017", calendar.getTime().toString());
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link library.Date#stringToDate2(java.lang.String)}.
	 */
	@Test
	public void testStringToDateSephie() {
		try {
			String dateString = "28/10/2017 20:10";
			GregorianCalendar calendar = ParseDate.stringToDateSephie(dateString);
			assertEquals("Sat Oct 28 20:10:00 IDT 2017", calendar.getTime().toString());
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link library.Date#stringToDateBoaz(java.lang.String)}.
	 */
	@Test
	public void testStringToDateBoaz() {
		try {
			String dateString = "12/05/17 11:48 AM";
			GregorianCalendar calendar = ParseDate.stringToDateBoaz(dateString);
			assertEquals("Fri May 12 11:48:00 IDT 2017", calendar.getTime().toString());
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link library.ParseDate#stringToDateDataBase(java.lang.String)}.
	 */
	@Test
	public void testStringToDateDataBase() {
		try {
			String dateString = "Fri Dec 01 15:37:01 IST 2017";
			GregorianCalendar calendar = ParseDate.stringToDateDataBase(dateString);
			assertEquals("Fri Dec 01 15:37:01 IST 2017", calendar.getTime().toString());
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

}
