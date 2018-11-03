import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrdersScreen extends JFrame implements ItemListener, ActionListener{

    final String []VEHICLE_LIST = {"Select vehicle", "Car", "Truck", "Boat", "MotorHome", "SnowMobile"};

    public OrdersScreen() {
	JFrame outline = new JFrame("Vehicle Ordering System");
	outline.setLayout(new BorderLayout());
	outline.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//Menu
	JMenuBar menuBar = new JMenuBar();
	//menu 1
	JMenu menu1 = new JMenu("File");
	JMenuItem menu1Item1 = new JMenuItem("Load");
	JMenuItem menu1Item2 = new JMenuItem("Exit");
	menu1Item2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	menu1.add(menu1Item1);
	menu1.add(menu1Item2);
	menuBar.add(menu1);
	//~menu 1
	
	//menu 2
	JMenu menu2 = new JMenu("Help");
	menuBar.add(menu2);
	//~menu 2
	
	outline.setJMenuBar(menuBar);
	//~Menu
	
	// title formating and adding to JFrame
	JLabel head = new JLabel("Vaishnavi Mande's Ordering System");
	Font font = new Font("Serif", Font.BOLD, 20);
	head.setFont(font);
	head.setForeground(Color.RED);
	outline.add(head, BorderLayout.NORTH);
	// ~ added the title to JFrame

	// vehicle data TextFields
	JPanel boxPanel = new JPanel();
	BoxLayout boxLayout = new BoxLayout(boxPanel, BoxLayout.Y_AXIS);
	boxPanel.setLayout(boxLayout);

	//1: Vehicle Type
	JPanel vehiclePanel = new JPanel(new FlowLayout());
	JLabel vehicleType = new JLabel("Vehicle Type: ");
	JComboBox<String> vehicleOption = new JComboBox<String>(VEHICLE_LIST);
	vehiclePanel.add(vehicleType);
	vehiclePanel.add(vehicleOption);
	boxPanel.add(vehiclePanel);
	//~1 

	//2
	JPanel modelPanel = new JPanel(new FlowLayout());
	JLabel labelModel = new JLabel("Model: ");
	JTextField vehicleModel = new JTextField("", 10);
	modelPanel.add(labelModel);
	modelPanel.add(vehicleModel);
	boxPanel.add(modelPanel);
	//~2

	//3
	JPanel colorPanel = new JPanel(new FlowLayout());
	JLabel labelColor = new JLabel("Color: ");
	JTextField vehicleColor = new JTextField("", 10);
	colorPanel.add(labelColor);
	colorPanel.add(vehicleColor);
	boxPanel.add(colorPanel);
	//~3

	//4
	JPanel costPanel = new JPanel(new FlowLayout());
	JLabel labelCost = new JLabel("Cost: ");
	JTextField vehicleCost = new JTextField("", 10);
	costPanel.add(labelCost);
	costPanel.add(vehicleCost);
	boxPanel.add(costPanel);
	//~4

	//5
	JPanel config1Panel = new JPanel(new FlowLayout());
	JLabel lconfig1 = new JLabel("");
	JComboBox config1 = new JComboBox();
	config1Panel.add(lconfig1);
	config1Panel.add(config1);
	boxPanel.add(config1Panel);
	//~5

	//6
	JPanel config2Panel = new JPanel(new FlowLayout());
	JLabel lconfig2 = new JLabel("");
	JComboBox config2 = new JComboBox();
	config2Panel.add(lconfig2);
	config2Panel.add(config2);
	boxPanel.add(config2Panel);
	//~6

	//Item State Change handled here 
	vehicleOption.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		String[] attributeLabels = {""};
		String[] choices1 = {""};
		String[] choices2 = {""};

		String item = (String)e.getItem();
		switch(item) {
		case Orders.CAR:
		    attributeLabels = Car.getAttributeLabels();
		    choices1 = Car.getCarTypeOptions();
		    choices2 = Car.getTowingOptions();
		    break;
		case Orders.BOAT:
		    attributeLabels = Boat.getAttributeLabels();
		    choices1 = Boat.getBoatTypeOptions();
		    choices2 = Boat.getBoatConstructOptions();
		    break;
		case Orders.MOTORHOME:
		    attributeLabels = MotorHome.getAttributeLabels();
		    choices1 = MotorHome.getMHFormOptions();
		    choices2 = MotorHome.getMHCategoryOptions();
		    break;
		case Orders.SNOWMOBILE:
		    attributeLabels = SnowMobile.getAttributeLabels();
		    choices1 = SnowMobile.getSMRidingOptions();
		    choices2 = SnowMobile.getSMSeatingOptions();
		    break;
		case Orders.TRUCK:
		    attributeLabels = Truck.getAttributeLabels();
		    choices1 = Truck.getTruckEngineOptions();
		    choices2 = Truck.getTruckSizeOptions();
		    break;    
		default:
		    return;
		}
		if (attributeLabels.length < 2) {
		    return;
		}
		lconfig1.setText(attributeLabels[0]);
		config1.removeAllItems();
		for (String s: choices1) {
		    config1.addItem(s);
		}

		config1Panel.add(lconfig1);
		config1Panel.add(config1);
		boxPanel.add(config1Panel);

		lconfig2.setText(attributeLabels[1]);
		config2.removeAllItems();
		for (String s: choices2) {
		    config2.addItem(s);
		}

		config2Panel.add(lconfig2);
		config2Panel.add(config2);
		boxPanel.add(config2Panel);

		//lconfig2.setText(text);
	    }
	});

	//Buttons Panel
	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new FlowLayout());
	JButton save = new JButton("Save");
	JButton prev = new JButton("Prev");
	JButton next = new JButton("Next");
	JButton first = new JButton("First");
	JButton last = new JButton("Last");
	JButton exit = new JButton("Exit");

	exit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});

	buttonPanel.add(save);
	buttonPanel.add(first);
	buttonPanel.add(prev);
	buttonPanel.add(next);
	buttonPanel.add(last);
	buttonPanel.add(exit);

	//~ Buttons

	outline.add(boxPanel, BorderLayout.NORTH);
	outline.add(buttonPanel, BorderLayout.SOUTH);

	outline.setSize(500,300);
	outline.setVisible(true);
    }


    public void actionPerformed(ActionEvent arg0) {}


    @Override
    public void itemStateChanged(ItemEvent arg0) {
	// TODO Auto-generated method stub

    }

    public static void main(String[] args) {
	OrdersScreen demo = new OrdersScreen();
    }
}
