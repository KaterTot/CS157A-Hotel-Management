import java.sql.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HotelStartScreen {
	HotelSQLProcedures pr = new HotelSQLProcedures();
	JFrame frame;
	JFrame manLogin;
	JFrame reg;

	public void createScreen() {
   frame = new JFrame("Start Screen");
   frame.setPreferredSize(new Dimension(2000,2000));
   JLabel label = new JLabel("Please choose one of the options below to continue");
   label.setFont(new Font("Serif", Font.PLAIN, 22));
   JPanel labelPanel = new JPanel();
   labelPanel.setMaximumSize(new Dimension(500, 100));
   labelPanel.add(Box.createHorizontalStrut(40));
   labelPanel.add(label);
  
   JButton manager = new JButton("I am a manager");
   JButton customer = new JButton("I am a customer");
   JPanel panel = new JPanel();
   JPanel finalPanel = new JPanel();
   BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
   finalPanel.setLayout(boxlayout);
   panel.add(manager);   
   panel.add(customer);
   finalPanel.add(Box.createVerticalStrut(100));
   finalPanel.add(labelPanel);
   finalPanel.add(panel);
   finalPanel.add(Box.createVerticalStrut(100));
   frame.add(finalPanel);
   
   manager.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
		   frame.setVisible(false);
		   HotelStartScreen screen = new HotelStartScreen();
		   screen.managerLogin("manager");
	   }
   });
   
   customer.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
		   frame.setVisible(false);
		   HotelStartScreen screen = new HotelStartScreen();
		   screen.managerLogin("customer");
	   }
   });
   
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.pack();
     frame.setVisible(true);
	}
	
	public void managerLogin(String position)   {
		manLogin = new JFrame("Start Screen");
		manLogin.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please log in to continue");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel username = new JLabel("Username:");
		JLabel password = new JLabel("Password:");
		JTextField user = new JTextField();
		JPasswordField pass = new JPasswordField();
		user.setPreferredSize(new Dimension(100, 20));
		pass.setPreferredSize(new Dimension(100, 20));
		
		JButton button = new JButton("Log in");
		JButton back = new JButton("Back");
		
		JPanel labelPanel = new JPanel();
		labelPanel.add(Box.createVerticalStrut(50));
		labelPanel.add(label);
		labelPanel.setMaximumSize(new Dimension(300, 50));
		
		JPanel uPanel = new JPanel();
		uPanel.add(username);
		uPanel.add(user);
		uPanel.setMaximumSize(new Dimension(200, 30));
		
		JPanel pPanel= new JPanel();
		pPanel.add(password);
		pPanel.add(pass);
		pPanel.setMaximumSize(new Dimension(200, 50));
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(labelPanel);
		finalPanel.add(uPanel);
		finalPanel.add(pPanel);
		finalPanel.add(button);
		if(position.equals("customer")) {
			JButton reg = new JButton("Create Account");
			finalPanel.add(reg);
			reg.addActionListener(new ActionListener()  {
				public void actionPerformed(ActionEvent e)   {
					manLogin.setVisible(false);
					HotelStartScreen screen = new HotelStartScreen();
					screen.registerScreen();
				}
			});
		}
		finalPanel.add(back);
		
		back.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				manLogin.setVisible(false);
				HotelStartScreen screen = new HotelStartScreen();
				screen.createScreen();
			}
		});
		
		button.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   String uName = user.getText();
				   String passw = pass.getText();
				   int cID = pr.validateLogin(uName, passw, position);
				   if(cID > 0) {
					   manLogin.setVisible(false);
					   if(position.equals("manager")) {
					      HotelManagerScreen screen = new HotelManagerScreen();
					      screen.createScreen();
					   }
					   else {
						   HotelCustomerScreen screen = new HotelCustomerScreen();
						   screen.createScreen(cID);
					   }
				   }
				   else {
					   JOptionPane.showMessageDialog(frame,"You have entered " +
			                     "incorrect username or password. Please try again.");
					   user.setText("");
					   pass.setText("");
				   }
		   }});
		
		manLogin.add(finalPanel);
		manLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    manLogin.pack();
	    manLogin.setVisible(true);
	}
	
	public void registerScreen() {
		reg = new JFrame("Register Screen");
		reg.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please enter all required information below:");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel username = new JLabel("Username:");
		JLabel password = new JLabel("Password:");
		JLabel confirm = new JLabel("Confirm password:");
		JLabel card = new JLabel("Credit card number:");
		JTextField user = new JTextField();
		JTextField pass = new JTextField();
		JTextField cred = new JTextField();
		JTextField conf = new JTextField();
		user.setPreferredSize(new Dimension(100, 20));
		pass.setPreferredSize(new Dimension(100, 20));
		cred.setPreferredSize(new Dimension(100, 20));
		conf.setPreferredSize(new Dimension(100, 20));
		
		JPanel lPanel = new JPanel();
		lPanel.add(label);
		lPanel.setMaximumSize(new Dimension(500, 50));
		
		JPanel u = new JPanel();
		u.add(username);
		u.add(user);
		u.setMaximumSize(new Dimension(500, 50));
		
		JPanel p = new JPanel();
		p.add(password);
		p.add(pass);
		p.setMaximumSize(new Dimension(500, 50));
		
		JPanel con = new JPanel();
		con.add(confirm);
		con.add(conf);
		con.setMaximumSize(new Dimension(500, 50));
		
		JPanel c = new JPanel();
		c.add(card);
		c.add(cred);
		c.setMaximumSize(new Dimension(500, 50));
		
		JButton back = new JButton("Back");
		JButton create = new JButton("Create Account");
		JPanel cr = new JPanel();
		cr.add(back);
		cr.add(create);
		cr.setMaximumSize(new Dimension(500, 50));
		
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.PAGE_AXIS));
		finalPanel.add(lPanel);
		finalPanel.add(u);
		finalPanel.add(p);
		finalPanel.add(con);
		finalPanel.add(c);
		finalPanel.add(cr);
		
		back.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				reg.setVisible(false);
				HotelStartScreen screen = new HotelStartScreen();
				screen.managerLogin("customer");
			}
		});
		
		create.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				String username = user.getText();
				String password = pass.getText();
				String confirm = conf.getText();
				String cardNum = cred.getText();
				if(username.isEmpty() || password.isEmpty() ||
						confirm.isEmpty() || cardNum.isEmpty()) {
					JOptionPane.showMessageDialog(reg, "Please enter all required information");
					user.setText("");
					pass.setText("");
					conf.setText("");
					cred.setText("");
				}
				else if(!password.equals(confirm)) {
					JOptionPane.showMessageDialog(reg, "Password and confirm password fields must match");
					pass.setText("");
					conf.setText("");
				}
				else {
					int ret = pr.createAccount(username, password, cardNum);
					if(ret == 1062)   {
						JOptionPane.showMessageDialog(reg, "The user with this username already exists. Please choose a different username");
						user.setText("");
						pass.setText("");
						conf.setText("");
						cred.setText("");
					}
					else {
						reg.setVisible(false);
						HotelStartScreen screen = new HotelStartScreen();
						screen.registerConfirm();
					}
				}
			}
		});
		
		reg.add(finalPanel);
		reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    reg.pack();
	    reg.setVisible(true);
	}
	
	public void registerConfirm()   {
		frame = new JFrame("Start Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Thank you for your registration! You are all set!");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JButton button = new JButton("Begin");
		
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(500, 50));
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		
		lPanel.add(label);
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.PAGE_AXIS));
		finalPanel.add(lPanel);
		finalPanel.add(bPanel);
		frame.add(finalPanel);
		
		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				frame.setVisible(false);
				HotelStartScreen screen = new HotelStartScreen();
				screen.managerLogin("customer");
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
}
