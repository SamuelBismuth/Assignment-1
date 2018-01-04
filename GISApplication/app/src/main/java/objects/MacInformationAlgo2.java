package objects;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class implements @see {@link MacInformation}.
 * This class represents a object {@link MacInformation} for the data of the algorithm 2.
 *
 * @author Orel and Samuel.
 */
public class MacInformationAlgo2 extends MacInformation {

    private double pi;

    /**
     * Constructor.
     *
     * @param coordinates
     * @param wifi
     * @param pi
     */
    public MacInformationAlgo2(EarthCoordinate coordinates, Wifi wifi, double pi) {
        super(coordinates, wifi);
        this.pi = pi;
    }

    //Getters.

    /**
     * @return pi.
     */
    @Override
    public double getWeigthSignal() {
        return pi;
    }

    /**
     * @return signal.
     */
    @Override
    public double getSignal() {
        if (pi == 0) return -120;
        return Math.sqrt(1 / pi);
    }

}
