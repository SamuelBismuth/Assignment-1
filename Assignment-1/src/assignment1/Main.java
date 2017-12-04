package assignment1;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The Main class.
 * About the warnings :
 * In the book : "Introduction to Java Programming," by Liang (10th ed),
 * "Note: If you use an IDE such as Eclipse or NetBeans, 
 * you will get a warning to ask you to close the input to prevent a potential resource leak
 * Ignore the warning because the input is automatically closed when your program is terminated. 
 * In this case, there will be no resource leaking." (page 39)
 * @author Orel and Samuel.
 */

public class Main {

	/**
	 * The main method.
	 * To run the project, you need to put the folder into you workspace.
	 * And also import the two api's.
	 * @See NOTICE for more details.
	 * @param args.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String folderPathWorkspace = new File(".").getAbsolutePath();
		System.out.println("Input the name of the folder please :");
		String folderName = new Scanner(System.in).nextLine();
		String folderPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1) + folderName;
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		
		//beginning the algorithm.
	
		Read rd = new ReadFolder(array);
		rd.read(folderPath);
		Collections.sort(array);
		new WriteCsv(array);
		UserChoice user = new UserChoice();
		user.userChoice(array);
	}
}
