import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      I   STE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        09/24/2018          <BR>
 * <BR>
 * Class:       Boat
 * Purpose:     Boat class stores the boat related information
 *              The properties of boat that are stored are - Model, Color, Cost, type and construct
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Order
 * @see Car
 */
public class Boat extends Vehicle {
    private String boatType;
    private String boatConstruct;

    public static String VEHICLE_IS = "Boat";
    public String getBoatType() {return boatType;}
    public String getBoatConstruct() {return boatConstruct;}
    public void setBoatType(String type) {this.boatType = type;}
    public void setBoatConstruct(String construct) {this.boatConstruct = construct;}


    final static String [] TYPE = {"Pontoon", "PWC", "Sailboat"};
    final static String [] CONSTRUCT = {"Wood", "Fiberglass", "Steel"};
    final static String BoatConstrctPrompt = "What is the boat's construcion?"; //\n1.Wood\n2.Fiberglass\n3.Steel\nChoice: ";
    final static String BoatTypePrompt = "What type of boat is this?"; //\n1.Pontoon\n2.PWC\n3.Sailboat\nChoice: ";
    final static String []ATTRIBUTE_LABLES = {"BoatType", "BoatConstruct"};

    public static String [] getAttributeLabels(){
	return ATTRIBUTE_LABLES;
    }
    
    public static String [] getBoatTypeOptions() {
	return TYPE;
    } 
    
    public static String [] getBoatConstructOptions() {
	return CONSTRUCT;
    }
    Boat(){
	//this.takeOrder();
    }
    Boat(String msg){
	printMsg(msg);
	this.takeOrder();
    }
    /**
     * Purpose: Reads the serialized object from file
     * Converts it into the Boat object
     * @param dis
     * @throws Exception
     */
    public void readBoatInfoFromFile(DataInputStream dis) throws Exception{
	setVehicleType(VEHICLE_IS);
	readVehicleInfoFromFile(dis);
	dis.readUTF();
	dis.readUTF();
	setBoatType(dis.readUTF());
	setBoatConstruct(dis.readUTF());
    }

    /**
     * Purpose: Writes the serialized Boat object to file
     * @param dos
     * @throws Exception
     */
    public void writeBoatInfoToFile(DataOutputStream dos) throws Exception {
	this.writeVehicleInfoToFileOld(dos);
	dos.writeUTF(getBoatType());
	dos.writeUTF(getBoatConstruct());
    }

    /**
     * Calculates the boat specific gasMileage
     */
    public double gasMileage() {
	double mileage = 0.0;
	switch(boatType) {
	case "Pontoon":
	    mileage = 3.5;
	    break;
	case "PWC":
	    mileage = 2.2;
	    break;
	case "Sailboat":
	    mileage = 0;
	    break;
	default:
	    mileage = 0;
	}
	return mileage;
    }
    
    /**
     * Used to take the boat specific orders from the user
     */
    public void takeOrder() {
	super.takeOrder(VEHICLE_IS);
	setBoatType(getSelection(TYPE, showMenu(BoatTypePrompt, TYPE)));
	setBoatConstruct(getSelection(CONSTRUCT, showMenu(BoatConstrctPrompt, CONSTRUCT)));
    }
    
    public String toString(){
	String s = "";
	s += 	super.toString() +
		System.getProperty("line.separator") +
		"\tType:\t\t" + String.format("%s", this.getBoatType()) +
		System.getProperty("line.separator") +
		"\tConstruct:\t" + String.format("%s", this.getBoatConstruct());
	return s;
    }
}