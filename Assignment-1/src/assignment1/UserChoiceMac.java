package assignment1;

import java.util.Scanner;

public class UserChoiceMac implements UserChoice {

	@SuppressWarnings("resource")
	public FilteringKml userChoice() {
		System.out.println("Please input a mac address.");
		String macName = new Scanner(System.in).nextLine();
		//return new FilteringCsvMac(macName);
		return null;
	}

}
