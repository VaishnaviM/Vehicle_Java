/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      I   STE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        09/24/2018          <BR>
 * <BR>
 * Interface:   Vinfo
 * Purpose:     Stores the applicable functionality of gasMilage()
 * 				This interface stores the constants for individual classes
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Order
 * @see Car
 */
public interface Vinfo {
    double SNOWMOBILE = 15;
    double SNOWMOBILE_TWOPERSONS = -2;

    double MOTORHOME_CLASS_A = 3;
    double MOTORHOME_CLASS_B = 2;
    double MOTORHOME_CLASS_C = 1;
    double MOTORHOME_MAX_MILAGE = 100;

    double CAR_SEDAN = 23.7;
    double CAR_COUPE = 28.2;
    double CAR_WAGON = 19.5;
    double CAR_TOW_MILAGE = 3.0;

    double gasMileage();
}
