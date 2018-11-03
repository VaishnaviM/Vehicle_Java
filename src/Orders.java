import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Name:        Vaishnavi Mande     <BR>
 * Course:      ISTE.200.01         <BR>
 * Homework:    TruckCar            <BR>
 * Date:        09/24/2018          <BR>
 * <BR>
 * Class:       Orders
 * Purpose:     Orders class acts as a controller class.
 *              It is responsible for calling the vehicle classes - truck and car as per request
 * Logic:       Order class reads the input from user
 *              depending on the user input, it calls the (vehicle)class constructors
 *              and appends the class objects in an ArrayList
 *              When user is done with adding input, Order class prints the Array Objects
 * <BR>
 * @author Vaishnavi
 * @version 1.0
 * @see Truck
 * @see Car
 */
public class Orders {
    static ArrayList<Vehicle> list = new ArrayList<Vehicle>();
    static String datFileName = "Order.dat";
    static String txtFileName = "Order.txt";


    static final String CAR = "Car";
    static final String TRUCK = "Truck";
    static final String BOAT = "Boat";
    static final String SNOWMOBILE = "SnowMobile";
    static final String MOTORHOME = "MotorHome";
    static final String ORDERING_SYSTEM = "Vaishnavi Mande's Ordering System";
    static final String THANKYOU = "Thank you for using Vaishnavi Mande's ordering system";
    
    public void takeOrder() {
	Scanner scan = new Scanner(System.in);
	System.out.println(ORDERING_SYSTEM);
	while(true) {
	    System.out.println("Do you want to order a Truck (T/t) or Car (C/c) or Boat (B/b) or SnowMobile(s/S) or MotorHome(m/M)");
	    String choice = scan.next();
	    switch (choice.toLowerCase()){
	    case "c":
		Car car = new Car("Input Car order");
		list.add(car);
		break;
	    case "t":
		Truck truck = new Truck("Input truck order");
		list.add(truck);
		break;
	    case "b":
		Boat boat = new Boat("Input Boat order");
		list.add(boat);
		break;
	    case "s":
		SnowMobile sb = new SnowMobile("Input SnowMobile order");
		list.add(sb);
		break;
	    case "m":
		MotorHome mh = new MotorHome("Input MotorHome order");
		list.add(mh);
		break;
	    default:
		System.out.println("Invalid input");
	    }

	    System.out.println("Do you want to order another vehicle?");
	    scan.nextLine();
	    String str = scan.nextLine();
	    if(!str.toLowerCase().equals("y")){
		break;
	    }
	}
	scan.close();
    }

    public void loadOrder() {
	try {
	    DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(datFileName)));
	    while(dis.available() > 0) {
		String vehicleType = dis.readUTF();
		switch (vehicleType){
		case CAR:
		    Car car = new Car();
		    car.readCarInfoFromFile(dis);
		    list.add(car);
		    break;
		case TRUCK:
		    Truck truck = new Truck();
		    truck.readTruckInfoFromFile(dis);
		    list.add(truck);
		    break;
		case BOAT:
		    Boat boat = new Boat();
		    boat.readBoatInfoFromFile(dis);
		    list.add(boat);
		    break;
		case SNOWMOBILE:
		    SnowMobile sb = new SnowMobile();
		    sb.readSnowMobInfoFromFile(dis);
		    list.add(sb);
		    break;
		case MOTORHOME:
		    MotorHome mh = new MotorHome();
		    mh.readMotorHomeInfoFromFile(dis);
		    list.add(mh);
		    break;
		default:
		    System.out.println("Invalid input as vehicleType is "+ vehicleType);
		}
	    }
	}catch(Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void saveOrder() {
	try {
	    DataOutputStream ds = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(datFileName)));
	    BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFileName), "UTF-8"));
	    br.write(ORDERING_SYSTEM);
	    
	    for(Vehicle vh : list) {
		br.write(vh.toString());
		if(vh instanceof Car) {
		    Car car = (Car) vh;
		    car.writeCarInfoToFile(ds);
		}else if(vh instanceof Truck) {
		    Truck truck = (Truck) vh;
		    truck.writeTruckInfoToFile(ds);
		}else if(vh instanceof Boat) {
		    Boat boat = (Boat)vh;
		    boat.writeBoatInfoToFile(ds);
		}else if(vh instanceof SnowMobile) {
		    SnowMobile sb = (SnowMobile)vh;
		    sb.writeSBInfoToFile(ds);
		}else if(vh instanceof MotorHome) {
		    MotorHome mh = (MotorHome)vh;
		    mh.writeMHInfoToFile(ds);
		}
	    }
	    br.write(System.getProperty("line.separator"));
	    br.write(THANKYOU);
	    br.flush();
	    br.close();
	    ds.flush();
	    ds.close();
	}catch(Exception e) {
	    e.printStackTrace();
	}
    }

    public void displayOrders(String msg) {
	System.out.println(msg);
	for (Object obj : list){
	    System.out.println(obj + "\n");
	}
	System.out.println(THANKYOU);
    }

    /*public static void main(String[] args){	
	Demo d = new Demo();
	Orders order = new Orders();
	//System.out.println("Loading");
	order.loadOrder();
	//System.out.println("Loaded, Ttaking");
	order.displayOrders("Loading orders from the file:");
	order.takeOrder();
	//System.out.println("Taken, saving");
	order.saveOrder();
	//System.out.println("saved");
	order.displayOrders("Display all the orders:");
    }*/
}