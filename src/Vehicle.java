/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      I   STE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        09/24/2018          <BR>
 * <BR>
 * Class:       Vehicle
 * Purpose:     Vehicle class stores the vehicle related information and serves as the base class for truck, car and boat classes
 *              The generic properties of vehicle that are stored are - Model, Color, Cost,
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Order
 * @see Car
 */

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Vehicle implements Vinfo {
    private String vehicleType;
    private String model;
    private String color;
    private double cost;

    Scanner scan = new Scanner(System.in);
    //Should I make scan static so that it will be initialized once which would be efficient 

    final static String SELECTION = "Choice:";
    final static String INVALID_INPUT = "\nInvalid Input";

    public void takeOrder(String type){
	printMsg("\nEntering " + type+ " order\n");
	//scan = new Scanner(System.in);
	setVehicleType(type);
	setModel(getStringFromConsole("Model: "));
	setColor(getStringFromConsole("Color: "));
	setCost(getDoubleFromConsole("Cost: "));
    }

    Vehicle(){}

    public void readVehicleInfoFromFile(DataInputStream dis) throws Exception {
	setModel(dis.readUTF());
	setColor(dis.readUTF());
	setCost(dis.readFloat());
    }

    public void writeVehicleInfoToFileOld(DataOutputStream dos) throws Exception {
	dos.writeUTF(getVehicleType());
	dos.writeUTF(getModel());
	dos.writeUTF(getColor());
	dos.writeDouble(getCost());
    }
    public void writeVehicleInfoToFile(Object obj) throws Exception {
	if (obj instanceof DataOutputStream) {
	    DataOutputStream dos = (DataOutputStream)obj;
	    dos.writeUTF(getVehicleType());
	    dos.writeUTF(getModel());
	    dos.writeUTF(getColor());
	    dos.writeDouble(getCost());
	}
	else if(obj instanceof BufferedWriter) {
	    BufferedWriter bw = (BufferedWriter)obj;
	    bw.write(getVehicleType());
	    bw.write(getModel());
	    bw.write(getColor());
	    bw.write(""+getCost());
	}
    }
    public String getColor() {
	return color;
    }

    public String getModel() {
	return model;
    }

    public double getCost() {
	return cost;
    }

    public String getVehicleType() {
	return vehicleType;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public void setCost(double cost) {
	this.cost = cost;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
    }

    public void printMsg(String msg){
	System.out.print(msg);
    }

    public double getDoubleFromConsole(String msg){	
	double cost = 0.0;
	while(true) {
	    printMsg(msg);
	    if (scan.hasNextDouble()) {
		try {
		    cost = scan.nextDouble();
		    if(cost <= 0.0) {
			printMsg("Please enter a valid price value greater than 0");
			continue;
		    }
		    else {
			break;
		    }
		}catch (NumberFormatException e){
		    printMsg("Please enter a valid number");
		    continue;
		}catch(InputMismatchException e) {
		    printMsg("Please enter a valid number");
		}catch(Exception e) {
		    printMsg("Exception while reading" + msg + ":" + e + "\n");
		    continue;
		}
	    }
	    else {
		scan.nextLine();
		printMsg("Invalid dollar amount, do not use $ or , in the entered cost. Try again\n");
	    }
	}
	return cost;
    }


    public String getStringFromConsole(String msg){
	printMsg(msg);
	String input = "";
	try{
	    input = scan.nextLine();
	}catch (Exception e){
	    printMsg("Exception while reading" +  msg + ":" + e + "\n");
	}
	return input;
    }


    public String toString(){
	final String s = 	 
		System.getProperty("line.separator") +
		this.getVehicleType() +
		System.getProperty("line.separator") +
		"\tModel:\t\t" + this.getModel() +
		System.getProperty("line.separator") +
		"\tColor:\t\t" + this.getColor() +
		System.getProperty("line.separator") +
		"\tCost:\t\t"  + String.format( "$%,.2f", this.getCost() ) +
		System.getProperty("line.separator") +
		"\tMPG/GPH:\t" + String.format( "%,.1f", this.gasMileage() );
	return s;
    }

    /** Old showMenu() which used to return the selected option id.
     * @param prompt
     * @param choices
     * @return id
     */
    public String showMenuOld(String prompt, String[] choices){
	String retval = "";
	int choice = -1;
	while (true) {
	    printMsg(prompt);
	    try {
		choice = scan.nextInt();
		if (choice < 1 || choice > choices.length) {
		    continue;
		} else {
		    retval = choices[choice - 1];
		    break;
		}
	    } catch (Exception e) {
		printMsg(INVALID_INPUT+" Enter input 1 through "+choices.length);
	    }
	}
	return retval;
    }

    /**
     * New showMenu() which returns the 
     * @param prompt
     * @param choices
     * @return index of choice selected
     */
    public int showMenu(String prompt, String[] choices){
	int len = choices.length;
	printMsg(prompt);

	int choice = -1;
	while (true) {
	    scan.nextLine();
	    printMsg("\n");
	    for(int i=0; i<len; i++) {
		printMsg((i+1)+"."+choices[i]+"\n");
	    }
	    printMsg(SELECTION);
	    if(scan.hasNextInt()) {
		try {
		    choice = scan.nextInt();
		    //scan.nextLine();
		    if (choice < 1 || choice > choices.length) {
			printMsg("Please enter a valid choice between 1 and " + choices.length + "\n");
			continue;
		    } else {
			choice = choice - 1;
			break;
		    }
		} catch (Exception e) {
		    printMsg(INVALID_INPUT + " from exception\n");
		    continue;
		}
	    }
	}
	return choice;
    }

    String getSelection(String[] options, int option) {
	return(options[option]);
    }

    public abstract double gasMileage();


}