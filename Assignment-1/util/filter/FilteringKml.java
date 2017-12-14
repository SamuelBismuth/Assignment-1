package filter;

import java.util.Scanner;

public abstract class FilteringKml {

	@SuppressWarnings("resource")
	public static String getFileName() {
		System.out.println("Input a name for the kml file you want to create : ");
		return new Scanner(System.in).nextLine();
	}
	
}
