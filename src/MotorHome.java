import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      ISTE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        10/15/2018          <BR>
 * <BR>
 * Class:       MotorHome
 * Purpose:     MotorHome class contains MotorHome features - model, color, cost, form, category
 *              It stores the all the MotorHome related information
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Vehicle
 */


public class MotorHome extends Vehicle {
    private String motorHomeForm;
    private String motorHomeCategory;

    public static String VEHICLE_IS = "MotorHome";
    final static String [] FORM = {"Mobile","Temporary","Dwelling"};
    final static String [] CATEGORY = {"ClassA(Integrated)","ClassB(Semi-integrated)", "ClassC(Alcove)"};
    final static String []ATTRIBUTE_LABLES = {"MH_Form", "MH_Category"};

    final static String MotorHomeFormPrompt = "In which form of accomodation would you classigy the motorHome?";
    //+ "1.Mobile\n2.Temporary\n3.Dwelling";
    final static String MotorHomeCategoryPrompt= "What is the class classification of the motorHome?";
    //+ "\n1.Class A(Integrated)\n2.Class B(Semi-integrated)\n3.Class C(Alcove)";


    public static String [] getAttributeLabels(){
	return ATTRIBUTE_LABLES;
    }
    public static String [] getMHFormOptions(){
	return FORM;
    }
    
    public static String [] getMHCategoryOptions() {
	return CATEGORY;
    }
    /**
     * Accessor for MotorHomeForm
     * @return MotorHomeForm value
     */
    public String getMotorHomeForm() {
	return motorHomeForm;
    }

    /**
     * Mutator of MotorHomeForm 
     * @param motorHomeForm
     */
    public void setMotorHomeForm(String motorHomeForm) {
	this.motorHomeForm = motorHomeForm;
    }

    /**
     * Accessor for motorHomeCategory
     * @return motorHomeCategory
     */
    public String getMotorHomeCategory() {
	return motorHomeCategory;
    }

    /**
     * Mutator for for motorHomeCategory
     * @param motorHomeCategory
     */
    public void setMotorHomeCategory(String motorHomeCategory) {
	this.motorHomeCategory = motorHomeCategory;
    }


    /**
     * MotorHome :  default constructor of MotorHome
     */
    MotorHome(){
	//this.takeOrder();
    }
    MotorHome(String msg){
	printMsg(msg);
	this.takeOrder();
    }
    /**
     * Purpose: Reads the serialized object from file
     * 		Converts it into the MotorHome object
     * @param dis
     * @throws Exception
     */
    public void readMotorHomeInfoFromFile(DataInputStream dis) throws Exception{
	setVehicleType(VEHICLE_IS);
	readVehicleInfoFromFile(dis);
	dis.readUTF();
	dis.readUTF();
	setMotorHomeForm(dis.readUTF());
	setMotorHomeCategory(dis.readUTF());
    }
    
    /**
     * Purpose: Writes the serialized MotorHome object to file@param dos
     * @throws Exception
     */
    public void writeMHInfoToFile(DataOutputStream dos) throws Exception {
	this.writeVehicleInfoToFileOld(dos);
	dos.writeUTF(getMotorHomeForm());
	dos.writeUTF(getMotorHomeCategory());
    }
    /**
     * getMilage() implementation of MotorHome
     * @return mileage value
     * 
     * Please add following code to Vinfo.java
     * double MOTORHOME_CLASS_A = 3;
     * double MOTORHOME_CLASS_B = 2;
     * double MOTORHOME_CLASS_C = 1;
     * double MOTORHOME_MAX_MILAGE = 100;
     */
    public double gasMileage() {
	double mileage = 0.0;
	switch(motorHomeCategory) {
	case "Class A(Integrated)":
	    //why CATEEGORY[0] does not work
	    mileage = MOTORHOME_MAX_MILAGE/MOTORHOME_CLASS_A;
	    break;
	case "Class B(Semi-integrated)":
	    mileage = MOTORHOME_MAX_MILAGE/MOTORHOME_CLASS_B;
	    break;
	case "Class C(Alcove)":
	    mileage = MOTORHOME_MAX_MILAGE/MOTORHOME_CLASS_C;
	    break;

	default:
	    mileage = MOTORHOME_MAX_MILAGE;
	}
	return mileage;
    }

    /**
     * Used to take the MotorHome specific orders from the user
     */
    public void takeOrder() {
	super.takeOrder(VEHICLE_IS);
	setMotorHomeForm(getSelection(FORM, showMenu(MotorHomeFormPrompt, FORM)));
	setMotorHomeCategory(getSelection(CATEGORY, showMenu(MotorHomeCategoryPrompt, CATEGORY)));
    }
    
    public String toString() {
	String s = super.toString() +
		System.getProperty("line.separator") +
		"\tForm:\t\t" + String.format(this.getMotorHomeForm()) +
		System.getProperty("line.separator") +
		"\tCategory:\t"+ String.format(this.getMotorHomeCategory());
	return s;
    }
}
