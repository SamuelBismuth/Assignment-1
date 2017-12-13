//package assignment;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * This class implements @see {@link UserChoice}.
// * This class ask the user the name of the mac adress he wants, and the number of mac adresse he wants.
// * @author Orel and Samuel.
// */
//public class UserChoiceMac implements UserChoice<Mac> {
//
//	private ArrayList<Mac> array;
//
//	/**
//	 * Constructor.
//	 * @param array
//	 */
//	public UserChoiceMac(ArrayList<Mac> array) {
//		this.array = array;
//	}
//
//	/**
//	 * This methode use a @see {@link Scanner} to recuperate the input of the user.
//	 * @exception NumberFormatException : Error on the input.
//	 */
//	@SuppressWarnings("resource")
//	public Filtering userChoice() throws InputException {
//		try {
//			System.out.println("Please input a mac address.");
//			String macName = new Scanner(System.in).nextLine();
//			if (!isMacExist(array, macName)) throw new InputException("The mac does not exist: " + macName);
//			return new FilteringCsvMac(macName);
//		}
//		catch (NumberFormatException ex) {
//			System.out.println("Error on the input. " + ex);
//			return userChoice();
//		}
//	}
//
//	//Private unimplemented method 
//
//	/**
//	 * @param array.
//	 * @param macName.
//	 * @return true is the mac exists.
//	 * @return false if the mac does't exist.
//	 */
//	private boolean isMacExist(ArrayList<Mac> array, String macName) {
//		for (Mac mac : array) if(mac.getMacName().equals(macName)) return true;
//		return false;
//	}
//
//}
