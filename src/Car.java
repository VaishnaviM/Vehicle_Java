import java.io.DataInputStream;
import java.io.DataOutputStream;


/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      ISTE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        09/24/2018          <BR>
 * <BR>
 * Class:       Car
 * Purpose:     Car class contains car features - model, color, cost, type, tow status
 *              It stores the all the car related information
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Truck
 * @see Car
 */


public class Car extends Vehicle{
    private String carType;
    private String carTowStat;

    public static String VEHICLE_IS = "Car";
    final static String [] TOWING = {"Towing package","No towing package"};
    final static String [] TYPE = {"Sedan", "Coupe", "Wagon"};
    final static String []ATTRIBUTE_LABLES = {"CarType", "Towing"};
    final static String CarTypePrompt = "What type of car is this?"; //\n1. Sedan\n2. Coupe\n3. Wagon\nChoice";
    final static String CarTowingPrompt = "Does this car have a towing package?"; //\n1. Yes\n2. No\nChoice: ";

    public String getCarType() {
	return carType;
    }
    public void setCarType(String type) {
	this.carType = type;
    }
    public String getCarTowStat() {
	return carTowStat;
    }
    public void setCarTowStat(String towStat) {
	this.carTowStat = towStat;
    }

    public static String [] getAttributeLabels(){
	return ATTRIBUTE_LABLES;
    }
    
    public static String[] getCarTypeOptions() {
	return TYPE;
    }
    
    public static String[] getTowingOptions() {
	return TOWING;
    }
    Car(){
	//this.takeOrder();
    }
    
    Car(String msg){
	printMsg(msg);
	this.takeOrder();
    }
    /**
     * Used to take the car specific orders from the user
     */
    public void takeOrder() {
	super.takeOrder(VEHICLE_IS);
	setCarType(getSelection(TYPE, showMenu(CarTypePrompt, TYPE)));
	setCarTowStat(getSelection(TOWING, showMenu(CarTowingPrompt, TOWING)));
    }

    /**
     * Purpose: Reads the serialized object from file
     *		Converts it into the MotorHome object
     * @param dis
     * @throws Exception
     */
    public void readCarInfoFromFile(DataInputStream dis) throws Exception{
	setVehicleType(VEHICLE_IS);
	readVehicleInfoFromFile(dis);
	dis.readUTF();
	dis.readUTF();
	setCarType(dis.readUTF());
	setCarTowStat(dis.readUTF());
    }

    /**
     * Purpose: Writes the serialized Car object to file
     * @param dos
     * @throws Exception
     */
    public void writeCarInfoToFile(DataOutputStream dos) throws Exception {
	this.writeVehicleInfoToFile(dos);
	dos.writeUTF(getCarType());
	dos.writeUTF(getCarTowStat());
    }

    public String toString(){
	String s = "";
	s += super.toString() +
		System.getProperty("line.separator") +
		"\tType:\t\t" + String.format("%s", this.getCarType()) +
		System.getProperty("line.separator") +
		"\tTowing:\t\t" + String.format("%s", this.getCarTowStat());
	return s;
    }

    /**
     * Calculates the car specific gasMileage
     */
    public double gasMileage() {
	double mileage = 0;
	double towMileage = 0;
	if(getCarTowStat().equals("Towing package")) {
	    towMileage = CAR_TOW_MILAGE;
	}
	switch(getCarType()) {
	case "Sedan":
	    mileage = CAR_SEDAN - towMileage;
	    break;
	case "Coupe":
	    mileage = CAR_COUPE - towMileage;
	    break;
	case "Wagon":
	    mileage = CAR_WAGON - towMileage;
	    break;

	default:
	    mileage = 0.0;
	}
	return mileage;
    }
}