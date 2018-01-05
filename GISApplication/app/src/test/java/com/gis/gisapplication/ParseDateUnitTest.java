/**
 * 
 */
package com.gis.gisapplication;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import libraries.InputException;
import libraries.ParseDate;

/**
 * @author Samuel
 *
 */
public class ParseDateUnitTest {

	/**
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
	 * Test method for {@link libraries.ParseDate#stringToDateDataBase(java.lang.String)}.
	 */
	@Test
	public void testStringToDateDataBase() {
		try {
			String dateString = "Mon Nov 27 16:16:45 IST 2017";
			GregorianCalendar calendar = ParseDate.stringToDateDataBase(dateString);
			assertEquals("Mon Nov 27 16:16:45 IST 2017", calendar.getTime().toString());
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

}
