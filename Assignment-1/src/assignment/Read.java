package assignment;

/**
 * This interface defines only one method : read.
 * Then, three classes implements this interface : @see {@link OpenFile}, @see {@link ReadFile}, @see {@link ReadFolder}.
 * @author Orel and Samuel.
 */
public interface Read {

	public void read(String folderName);
	
}