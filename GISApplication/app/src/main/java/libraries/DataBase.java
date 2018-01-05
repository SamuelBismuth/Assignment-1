package libraries;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

import objects.Filter;
import objects.CsvFile;
import objects.LineAlgo1;
import objects.Mac;
import objects.SampleScan;
import objects.WeigthAverage;

/**
 * This class includes all the static function which are in link to our Database.
 * Here all the function are static.
 * The explanation we could give (as students) is the next one :
 * A database is a place where all the data are stored.
 * Then, it's a static use.
 * In this Database we save all the data that the user made into his session.
 *
 * @author Orel and Samuel.
 */
public class DataBase {

    private static ArrayList<File> arrayFile = new ArrayList<File>();
    private static ArrayList<CsvFile> arrayCsvFile = new ArrayList<CsvFile>();
    private static ArrayList<SampleScan> arraySampleScan = new ArrayList<SampleScan>();
    private static ArrayList<Mac> arrayMac = new ArrayList<Mac>();
    private static ArrayList<LineAlgo1> arrayLineAlgo1 = new ArrayList<LineAlgo1>();
    private static ArrayList<WeigthAverage> arrayWeightAverage = new ArrayList<WeigthAverage>();
    private static Stack<Filter> filterStack = new Stack<Filter>();
    private static HashMap<String, ArrayList<SampleScan>> map = new HashMap<>();

    /**
     * Add a element to the map.
     *
     * @param path
     * @param arraySampleScan
     */
    public static void addMap(String path, ArrayList<SampleScan> arraySampleScan) {
        map.put(path, arraySampleScan);
    }

    /**
     * This function remove an element from the map.
     *
     * @param path
     */
    public static void removeMap(String path) {
        map.remove(path);
    }

    /**
     * This function remove a sampleScan array from the Database.
     *
     * @param arraySampleScanToremove
     */
    public static void removeArraySampleScan(ArrayList<SampleScan> arraySampleScanToremove) {
        arraySampleScan.removeAll(arraySampleScanToremove);
    }

    /**
     * @return map.
     */
    public static HashMap<String, ArrayList<SampleScan>> getMap() {
        return map;
    }

    /**
     * @return the arrayCsvFile
     */
    public static ArrayList<CsvFile> getArrayCsvFile() {
        return arrayCsvFile;
    }

    /**
     * @param arrayCsvFile the arrayCsvFile to set
     */
    public static void setArrayCsvFile(ArrayList<CsvFile> arrayCsvFile) {
        DataBase.arrayCsvFile = arrayCsvFile;
    }

    /**
     * @return the arraySampleScan
     */
    public static ArrayList<SampleScan> getArraySampleScan() {
        return arraySampleScan;
    }

    /**
     * @param arraySampleScan the arraySampleScan to set
     */
    public static void setArraySampleScan(ArrayList<SampleScan> arraySampleScan) {
        DataBase.arraySampleScan = arraySampleScan;
    }

    /**
     * @return the arrayMac
     */
    public static ArrayList<Mac> getArrayMac() {
        return arrayMac;
    }

    /**
     * @return the arrayWeightAverage
     */
    public static ArrayList<WeigthAverage> getArrayWeightAverage() {
        return arrayWeightAverage;
    }

    /**
     * @param arrayWeightAverage the arrayWeightAverage to set
     */
    public static void setArrayWeightAverage(ArrayList<WeigthAverage> arrayWeightAverage) {
        DataBase.arrayWeightAverage = arrayWeightAverage;
    }

    /**
     * Add an array of sampleScan to the {@link DataBase}.
     *
     * @param arrayToAdd
     * @return
     */
    public static boolean addAllArraySampleScan(ArrayList<SampleScan> arrayToAdd) {
        return arraySampleScan.addAll(arrayToAdd);
    }

    /**
     * @param arrayToAdd
     * @return true if the input have been good.
     */
    public static boolean addArrayCsvFile(ArrayList<CsvFile> arrayToAdd) {
        return arrayCsvFile.addAll(arrayToAdd);
    }

    /**
     * This method returns the number of wifi in  the Database.
     *
     * @return
     */
    public static int numberOfWifi() {
        int sum = 0;
        for (SampleScan sampleScan : arraySampleScan)
            sum += sampleScan.getArrayStrongerWifi().size();
        return sum;
    }

    /**
     * This method clear all the sampleScan of the Database.
     */
    public static void clear() {
        arraySampleScan.clear();
    }

    /**
     * This method check if the id exist into the database.
     *
     * @param id
     * @return false if the id does't exist.
     */
    public static boolean containsTheId(String id) {
        for (CsvFile csvFile : arrayCsvFile)
            if (csvFile.getId() == id) return true;
        return false;
    }

    /**
     * Push an object filter in the stack.
     *
     * @param filter
     */
    public static void pushFilter(Filter filter) {
        filterStack.push(filter);
    }

    /**
     * @return stack.
     */
    public static Stack<Filter> getFilterStack() {
        return filterStack;
    }

    /**
     * This function add a stack into the stack of the Database.
     *
     * @param stackFilterToPush
     */
    public static void pushAllSack(Stack<Filter> stackFilterToPush) {
        filterStack.addAll(stackFilterToPush);
    }

    /**
     * Pop a filter from the stack.
     *
     * @return
     */
    public static Filter popStack() {
        return filterStack.pop();
    }

    /**
     * @return arrayFile.
     */
    public static ArrayList<File> getArrayFile() {
        return arrayFile;
    }

    /**
     * Add a file into the Database.
     *
     * @param file
     */
    public static void addArrayFile(File file) {
        arrayFile.add(file);
    }

    /**
     * Add an arrayList of files into the Database.
     *
     * @param arrayFileToAdd
     */
    public static void addAllArrayFile(ArrayList<File> arrayFileToAdd) {
        arrayFile.addAll(arrayFileToAdd);
    }

    /**
     * This function may give the last modified time of the Database.
     *
     * @return the date.
     */
    public static Long getLastFileModification() {
        HashMap<File, Long> map = new HashMap<File, Long>();
        for (File file : getArrayFile())
            map.put(file, file.lastModified());
        return Collections.max(map.values());
    }

    /**
     * This function return if the file have been modified.
     *
     * @param date
     * @return true if the file is modified.
     */
    public static boolean isModified(long date) {
        return getLastFileModification() < date;
    }


}
