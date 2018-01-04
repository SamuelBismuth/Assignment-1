package filter;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.InputException;
import objects.Logic;
import objects.SampleScan;

/**
 * This class extends @see {@link Filtering}.
 * This class filtering the data by the place : only the scan in the area that the user choosed will appear in the kml place.
 *
 * @author Orel and Samuel.
 */
public class FilteringKmlPlace extends Filtering<SampleScan> {

    private EarthCoordinate coordinate;
    private double radius;

    /**
     * Constructor.
     *
     * @param coordinate
     * @param radius
     */
    public FilteringKmlPlace(EarthCoordinate coordinate, double radius) {
        this.coordinate = coordinate;
        this.radius = radius;
    }

    /**
     * This method filter by the place.
     *
     * @param array
     * @return array.
     */
    @Override
    public ArrayList<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
        ArrayList<SampleScan> arrayClone = (ArrayList<SampleScan>) array.clone();
        removeDuplicateMac(array);
        //array.removeIf(SampleScan -> SampleScan.getPointLocation().distanceTo(coordinate) > radius);
        for (SampleScan sampleScan : array) {
            if (sampleScan.getPointLocation().distanceTo(coordinate) > radius)
                arrayClone.remove(sampleScan);
        }
        if (array.size() == 0) throw new InputException("There array is empty.");
        return arrayClone;
    }

    @Override
    public String toString() {
        return "FilteringKmlPlace{" +
                "coordinate=" + coordinate.toString() +
                ", radius=" + radius +
                '}';
    }
}