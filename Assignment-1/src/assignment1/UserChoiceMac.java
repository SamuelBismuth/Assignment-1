package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

public class UserChoiceMac implements UserChoice {

	private ArrayList<Mac> array;

	public UserChoiceMac(ArrayList<Mac> array) {
		this.array = array;
	}

	/**
	 * 
	 */
	@SuppressWarnings("resource")
	public Filtering userChoice() throws InputException {
		try {
			System.out.println("Please input a mac address.");
			String macName = new Scanner(System.in).nextLine();
			if (!isMacExist(array, macName)) throw new InputException("The mac does not exist: " + macName);
			System.out.println("Please enter the number of mac you want to work with.");
			int macNb = Integer.parseInt(new Scanner(System.in).nextLine());
			Mac mac = findMac(array, macName);
			if (macNb > mac.getNumberOfMac()) throw new InputException("The number of mac is too much : the maximum is :" + mac.getNumberOfMac());
			return new FilteringCsvMac(macName);
		}
		catch (NumberFormatException ex) {
			System.out.println("Error on the input. " + ex);
			return userChoice();
		}
	}

	//Private unimplemented method 

	private boolean isMacExist(ArrayList<Mac> array, String macName) {
		for (Mac mac : array) if(mac.getMacName().equals(macName)) return true;
		return false;
	}

	private Mac findMac(ArrayList<Mac> array, String macName) {
		for (Mac mac : array) if(mac.getMacName().equals(macName)) return mac;
		return null;
	}



}
