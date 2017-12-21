package threads;

import java.util.ArrayList;

import algorithms.Mac;
import write.WriteFile;

public class WriteArray {
	
	private WriteFile<Mac> write;
	private ArrayList<Mac> arrayMac;
	
	public WriteArray(WriteFile<Mac> write, ArrayList<Mac> arrayMac) {
		this.write = write;
		this.arrayMac = arrayMac;
	}

	/**
	 * @return the write
	 */
	public WriteFile<Mac> getWrite() {
		return write;
	}

	/**
	 * @param write the write to set
	 */
	public void setWrite(WriteFile<Mac> write) {
		this.write = write;
	}

	/**
	 * @return the arrayMac
	 */
	public ArrayList<Mac> getArrayMac() {
		return arrayMac;
	}

	/**
	 * @param arrayMac the arrayMac to set
	 */
	public void setArrayMac(ArrayList<Mac> arrayMac) {
		this.arrayMac = arrayMac;
	}
	
	

}
