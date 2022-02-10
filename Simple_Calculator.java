
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Simple_Calculator extends JFrame implements ActionListener {


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Simple_Calculator inst = new Simple_Calculator();
				inst.setVisible(true);
				//inst.setLocationRelativeTo(null);
				inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	// Declaration of the various components for the frame
	JTextField integer1;
	JTextField integer2;
	JTextField result;
	JRadioButton add;
	JRadioButton sub;
	JRadioButton mul;
	JRadioButton div;
	JLabel label;
	ButtonGroup group;

	public Simple_Calculator() {

		// Calling the constructor of the super class
		// Jframe to display the title of the frame
		// creating a Content Pane to hold all
		// the components

		super("Calculation");

		// creating a frame with dimensions 400,150
		setSize(new Dimension(400, 150));

		// creating a Content Pane to hold all the components
		Container c = getContentPane();
		c.setLayout(null);

		// setting the background color to light beige color.
		c.setBackground(new Color(245, 245, 220));

		// Initializing the components
		integer1 = new JTextField();
		integer2 = new JTextField();
		result = new JTextField();
		label = new JLabel("=");
		group = new ButtonGroup();
		add = new JRadioButton("+");
		sub = new JRadioButton("-");
		mul = new JRadioButton("*");
		div = new JRadioButton("/");

		// Positioning the components in the right places
		integer1.setBounds(20, 10, 50, 20);
		integer2.setBounds(150, 10, 50, 20);
		result.setBounds(280, 10, 50, 20);
		label.setBounds(220, 10, 10, 20);
		add.setBounds(90, 10, 50, 15);
		sub.setBounds(90, 40, 50, 15);
		mul.setBounds(90, 70, 50, 15);
		div.setBounds(90, 100, 50, 15);

		// grouping the Radio Buttons to enable toggle between them.
		group.add(add);
		group.add(sub);
		group.add(mul);
		group.add(div);

		// Adding all components/objects to the content pane
		c.add(add);
		c.add(sub);
		c.add(mul);
		c.add(div);
		c.add(integer1);
		c.add(integer2);
		c.add(result);
		c.add(label);

		// used to invoke the actionPerformed method when any of the Radio Button is
		// clicked.
		add.addActionListener(this);
		sub.addActionListener(this);
		mul.addActionListener(this);
		div.addActionListener(this);

	}

	// implementing the abstract method ationPerformed from the interface
	// ActionListener
	@Override
	public void actionPerformed(ActionEvent evt) {

		JRadioButton selection = (JRadioButton) evt.getSource();
		float div_result;
		int res;
		int num1;
		int num2;

		// handling errors/exceptions using try-catch block
		// also doing the necessary calculations based on the Radio Button selected
		try {
			num1 = Integer.parseInt(integer1.getText());
			num2 = Integer.parseInt(integer2.getText());

			if (selection == add) {
				res = num1 + num2;
				result.setText(Integer.toString(res));
			} else if (selection == sub) {
				res = num1 - num2;
				result.setText(Integer.toString(res));
			} else if (selection == mul) {
				res = num1 * num2;
				result.setText(Integer.toString(res));
			} else if (selection == div) {
				// to handle Division by 0 error
				if (num2 == 0) {
					JOptionPane.showMessageDialog(null, "Error : Division by zero");
					integer1.setText("");
					integer2.setText("");
					result.setText("");
					group.clearSelection();
				} else {
					div_result = (float) num1 / num2;
					result.setText(Float.toString(div_result));
				}

			}

		}

		// catch block for handling no input error and non-integer input error
		catch (NumberFormatException e) {
			// To handle non-integer input & empty input in both text field.
			if (integer1.getText().equals("") | integer2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Error : No input in one or both text fields");
				integer1.setText("");
				integer2.setText("");
				result.setText("");
				group.clearSelection();

			} else {
				JOptionPane.showMessageDialog(null, "Error : Non integer inputs on one or both text fields");
				integer1.setText("");
				integer2.setText("");
				result.setText("");
				group.clearSelection();
			}
		}
	}
}

