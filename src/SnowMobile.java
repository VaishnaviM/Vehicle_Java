import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * SnowMobile class
 * This class extends from Vehicle,
 * uses its methods to get unique information for SnowMobile,
 * prints proper vehicle information by calling Vehicle.
 * The constructor takes a parameter to construct proper vehicle type from Vehicle.
 * Vehicle handles all user inputs 
 * 
 * @author Wenhao Luebs
 * @version 1.0
 * @since 10/12/2018
 * @see Vehicle
 */

public class SnowMobile extends Vehicle{

    public static String VEHICLE_IS = "SnowMobile";
    // array to hold valid values of Type of Riding and Seating
    private final static String [] RIDINGTYPE = {"Mountain", "On & Off Trail", "Rec/Utility"};
    private final static String [] SEATING = {"One person", "Two persons"};
    final static String []ATTRIBUTE_LABLES = {"SM_RidingType", "SM_Seating"};
    private final static String SnowMobileRidingTypePrompt =  "What type of riding is this?";
    private final static String SnowMobileSeatingPrompt = "What is the seating capacity?";
    private String ridingType;
    private String seating;

    // track user selection of Type of Riding and Seating
    // default index -1 to make default selection out of range
    int ridingTypeIndex = -1;
    int seatingIndex = -1;

    public static String [] getAttributeLabels(){
	return ATTRIBUTE_LABLES;
    }

    public static String [] getSMRidingOptions(){
	return RIDINGTYPE;
    }

    public static String [] getSMSeatingOptions(){
	return SEATING;
    }
    /** SnowMobile constructor 
     * @param type vehicle type entered by user from Order class
     * calls Vehicle constructor to create object
     * Vehicle prompts user input
     * Constructor also uses showMenu method in Vehicle to 
     * return index of user choice of riding type and seating capacity
     */
    public SnowMobile(){
	//this.takeOrder();
    }
    public SnowMobile(String msg) {
	printMsg(msg);
	this.takeOrder();
    }
    /**
     * Purpose: takes the snowMobile specific orders from user
     * 		and stores to the object
     */
    public void takeOrder() {
	super.takeOrder(VEHICLE_IS);
	setSeating(getSelection(RIDINGTYPE, showMenu(SnowMobileRidingTypePrompt, RIDINGTYPE)));
	setRidingType(getSelection(SEATING, showMenu(SnowMobileSeatingPrompt, SEATING)));
    }

    /**
     * Purpose: Reads the serialized object from file
     * 		Converts it into the SnowMobile object 
     * @param dis
     * @throws Exception
     */
    public void readSnowMobInfoFromFile(DataInputStream dis) throws Exception{
	setVehicleType(VEHICLE_IS);
	readVehicleInfoFromFile(dis);
	dis.readUTF();
	dis.readUTF();
	setSeating(dis.readUTF());
	setRidingType(dis.readUTF());
    }

    /**
     * @param dos: DataOutputStream
     * @throws Exception
     * Purpose: Writes the serialized SnowMobile object to file
     */
    public void writeSBInfoToFile(DataOutputStream dos) throws Exception {
	this.writeVehicleInfoToFileOld(dos);
	dos.writeUTF(getSeating());
	dos.writeUTF(getRidingType());
    }

    /** Accessor for type of riding calls getSelection method in Vehicle. 
     * @return String returns user choice of riding type.
     */
    public String getRidingType(){
	return ridingType;
    }

    /** Accessor for seating calls getSelection method in Vehicle. 
     * @return String returns user choice of seating capacity.
     */
    public String getSeating(){
	return seating;
    }

    public void setSeating(String seating){
	this.seating = seating;
    }

    public void setRidingType(String ridingType) {
	this.ridingType = ridingType;
    }

    /** printing method for the SnowMobile Object
     * format output
     */
    public String toString(){
	String s = "";
	s += 	super.toString() +
		System.getProperty("line.separator") +
		"\tType of Riding:" + String.format("%s", this.getRidingType()) +
		System.getProperty("line.separator") +
		"\tSeating: \t" + String.format("%s", this.getSeating());
	return s;
    }


    /** 
     * Accessor for boat gas mileage 
     * @return double returns gas mileage 
     */
    public double gasMileage() {
	double mileage = SNOWMOBILE;
	if(getSeating().equals(SEATING[1])){
	    mileage += SNOWMOBILE_TWOPERSONS;
	}
	return mileage;
    }

}// end of SnowMobile Class