package assignment;

import java.util.ArrayList;

public class temp {
	
public static void main(String[] args) {
	
	ArrayList<Scan> scan = new ArrayList<Scan>();
	//Scan temp = new Scan();
	ArrayList<Scan> scan2 = new ArrayList<Scan>();
	for (int i = 0; i < scan.size(); i++) {
		for (int j = 0; j < scan.get(i).getWifiNetworks(); j++) {
		//	for (int j2 = 0; j2 < temp.getWifiNetworks(); j2++) {
			//	if(scan.get(i).getArrayWifi().get(j).getMac().equals(temp.getArrayWifi().get(j2).getMac()))
					scan2.add(scan.get(i));
			}
		}
	}
}

