package runs;

import java.util.concurrent.Callable;

import filter.Filtering;
import objects.SampleScan;
import userChoice.UserChoice;

public class CallableUserChoice implements Callable<Filtering<SampleScan>> {

	private UserChoice userChoice;
	
	public CallableUserChoice(UserChoice userChoice) {
		this.userChoice = userChoice;
	}
	
	@Override
	public Filtering<SampleScan> call() throws Exception {
		System.out.println("Beginning the" + userChoice.toString());
		return userChoice.choice();
	}

}
