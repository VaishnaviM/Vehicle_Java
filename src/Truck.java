import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      I   STE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        09/24/2018          <BR>
 * <BR>
 * Class:       Truck
 * Purpose:     Truck class stores the truck related information
 *              The properties of truck that are stored are - Model, Color, Cost, TruckSize and Engine
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Order
 * @see Car
 */
public class Truck extends Vehicle{

    private String truckSize;
    private String truckEngine;

    public static String VEHICLE_IS = "Truck";
    final static String TruckSizePrompt = "What size truck is this?"; //\n1.Half-ton\n2.Full-ton\nChoice: ";
    final static String TruckEnginePrompt = "What is the engine size of the truck?"; //\n1.Really Big\n2.Not so big\nChoice: ";
    final static String [] ENGINE = {"Really big", "Not so big"};
    final static String [] SIZE = {"Half-ton", "Full-ton"};
    final static String []ATTRIBUTE_LABLES = {"TruckEngine", "TruckSize"};

    public static String [] getAttributeLabels(){
	return ATTRIBUTE_LABLES;
    }
    
    public static String [] getTruckSizeOptions(){
	return SIZE;
    }
    
    public static String [] getTruckEngineOptions(){
	return ENGINE;
    }
    public String getTruckSize(){
	return this.truckSize;
    }
    public String getTruckEngine(){
	return this.truckEngine;
    }
    public void setTruckSize(String size){
	this.truckSize = size;
    }
    public void setTruckEngine(String engine){
	this.truckEngine = engine;
    }

    Truck(){
	//this.takeOrder();
    }

    Truck(String msg){printMsg(msg);
	this.takeOrder();
    }
    /**
     * Purpose: Reads the serialized object from file
     * 		Converts it into the Truck object
     * @param dis
     * @throws Exception
     */
    public void readTruckInfoFromFile(DataInputStream dis) throws Exception{
	setVehicleType(VEHICLE_IS);
	readVehicleInfoFromFile(dis);
	dis.readUTF();
	dis.readUTF();
	setTruckSize(dis.readUTF());
	setTruckEngine(dis.readUTF());
    }
    
    /**
     * Purpose: Writes the serialized Truck object to file
     * @param dos
     * @throws Exception
     */
    public void writeTruckInfoToFile(DataOutputStream dos) throws Exception {
	this.writeVehicleInfoToFileOld(dos);
	dos.writeUTF(getTruckSize());
	dos.writeUTF(getTruckEngine());
    }
    
    public String toString(){
	String s = "";
	s += super.toString() +
		System.getProperty("line.separator") +
		"\tLoad:\t\t" + String.format(this.getTruckSize()) +
		System.getProperty("line.separator") +
		"\tEngine:\t\t" + String.format(this.getTruckEngine());
	return s;
    }

    /**
     * Calculated the truck specific gasMileage
     */
    public double gasMileage() {
	double truckCost = getCost();
	double milage = 50 - Math.sqrt(truckCost/10.0);
	return milage;
    }
    
    /**
     * Used to take the truck specific orders from the user
     */
    public void takeOrder() {
	super.takeOrder(VEHICLE_IS);
	setTruckSize(getSelection(SIZE, showMenu(TruckSizePrompt, SIZE)));
	setTruckEngine(getSelection(ENGINE, showMenu(TruckEnginePrompt, ENGINE)));
    }
}