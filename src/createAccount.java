import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
	
	

public class createAccount extends mainMenu implements ActionListener{

	protected static ArrayList<Account> allUserAccounts = new ArrayList<Account>();
	
	
	
	protected JFrame          frame;
	private JPanel          panel;
	protected JTextField      usernameField;
	protected  JTextField  	first;
	protected  JTextField  	last;
	protected  JTextField  	emailAdd;
	protected  JPasswordField  passwordField;
	protected  JPasswordField  confirmPassword;
	private  JLabel          warningLabel;

	
String password;
	        String confirmpassword;
	         String username;
	       String email;
	 
	    public createAccount() throws FileNotFoundException, IOException, ClassNotFoundException {
	        new GridBagLayout();
	        
	        ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("userinfo.txt"));
	    	allUserAccounts = (ArrayList<Account>) fileIn.readObject();
	    	fileIn.close();
	    	
	        panel = new JPanel();
	        panel.setLayout(new GridBagLayout());
	        panel.setBackground(Color.GREEN);

	        frame = new JFrame("Create a new account");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.getContentPane().add(BorderLayout.CENTER, panel);

	        JLabel firstnameLabel= new JLabel("First:");
	        JLabel lastnameLabel =new JLabel("Last");
	        JLabel emailLabel= new JLabel("E-mail:");
	        JLabel userLabel = new JLabel("Username:");
	        JLabel passwordLabel = new JLabel("Password:");
	        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
	       
	       
	       
	       
	       new GridBagLayout();
	        usernameField = new JTextField(10);
	        usernameField.addActionListener(this);
	        
	        GridBagConstraints c = new GridBagConstraints();
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	       
	        
	        c.fill = GridBagConstraints.BOTH;
	        c.weightx = 4.0;
	        c.weighty = 4.0;
	  add(usernameField,c);
	  
	       username= usernameField.getText();
	      usernameField.selectAll();
	        
	        
	        passwordField = new JPasswordField(10);
	        passwordField.addActionListener(this); 
	        add(passwordField,c);
	         this.password=new String(passwordField.getPassword());
	        passwordField.selectAll();
	        
	        confirmPassword = new JPasswordField(10);
	        confirmPassword.addActionListener(this); 
	        add(confirmPassword,c);
	        
			 this.confirmpassword=new String(confirmPassword.getPassword());
			confirmPassword.selectAll();
	        
			emailAdd= new JTextField(10);
		       emailAdd.addActionListener(this);
		       add(emailAdd,c);
		        this.email= emailAdd.getText();
		       emailAdd.selectAll();
		       
		       first= new JTextField(10);
		       first.addActionListener(this);
		       add(first,c);
		       String firstna= first.getText();
		       first.selectAll();
		       
		       last= new JTextField(10);
		       last.addActionListener(this);
		       add(last,c);
		       String lastna= last.getText();
		       last.selectAll();

	       GridBagConstraints right = new GridBagConstraints();
	        right.anchor = GridBagConstraints.WEST;
	        GridBagConstraints left = new GridBagConstraints();
	        left.anchor = GridBagConstraints.EAST;

	        right.weightx = (int) 3;
	        right.fill = GridBagConstraints.REMAINDER;
	        right.gridwidth = GridBagConstraints.REMAINDER;
	        // actual GUI
	       panel.add(firstnameLabel, left);
	        panel.add(first, right);
	        panel.add(lastnameLabel, left);
	        panel.add(last, right);
	        panel.add(emailLabel, left);
	        panel.add(emailAdd, right);
	        panel.add(userLabel, left);
	        panel.add(usernameField, right);
	        
	      panel.add(confirmPasswordLabel, left);
	        panel.add(confirmPassword, right);

	        panel.add(passwordLabel, left);
	        panel.add(passwordField, right);
	        
	        JButton createAccount = new JButton("Create this account");
	        frame.getContentPane().add(BorderLayout.SOUTH, createAccount);
	        createAccount.addActionListener(this);

	        warningLabel = new JLabel();
	        frame.getContentPane().add(BorderLayout.NORTH, warningLabel);

	        frame.setSize(300, 250);
	        frame.setVisible(true);
	 
	    }
	    
	    
	    public void actionPerformed(ActionEvent event) {
	    	boolean redo=false;
	    	//System.out.println(allUserAccounts.toString());
	    	/*Iterator<Account> iter34= allUserAccounts.iterator();
	    	Account showAccount;
	    	while(iter34.hasNext()){
	    	showAccount=iter34.next();	
	    	
	    	System.out.println(showAccount.getUsername());
	    	}*/
	    	
	    	String confirmpassword= new String(passwordField.getPassword());
	    	password= new String(confirmPassword.getPassword());
	    	
	        if (!confirmpassword.equals(password)) {
	        	
	            warningLabel.setText("Your passwords do not match! Please try again.");
	            redo=true;
	        
	        } 
	       else{
	    	   Iterator<Account> iter= allUserAccounts.iterator();
	    	   Account checkthisAccount;
	    	   while (iter.hasNext()){
	    		   checkthisAccount=iter.next();
	    		   if(checkthisAccount.getUsername().equals(usernameField.getText())){
	    			   warningLabel.setText("This Username is already taken! Please try again.");
	    			   redo=true;
	   				

	    		   }
	    	   }
	       }
	        	
	       
	        if(!redo){
	        	String thePassword= new String(passwordField.getPassword());
	        	try {
					createtheAccount(first.getText(),last.getText(),emailAdd.getText(),usernameField.getText(),thePassword);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	ObjectOutputStream fileOut;
				try {
					fileOut = new ObjectOutputStream(new FileOutputStream("userinfo.txt"));
					fileOut.writeObject(allUserAccounts);
		    	  fileOut.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(false);
	        }
	        	
	    }
	    /*public static void main(String[] args) throws IOException, ClassNotFoundException {
	    	
	    	ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("userinfo.txt"));
	    	allUserAccounts = (ArrayList<Account>) fileIn.readObject();
	    	fileIn.close();
	    	
	    	
	    	  SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					new forgotPassword();
				}
				});
	    	  	    	  FileOutputStream fos = new FileOutputStream("userinfo.txt");
	  		ObjectOutputStream oos = new ObjectOutputStream(fos);
	  		oos.writeObject(allUserAccounts);
	  		oos.close();
	    }*/
	    	  

		
	    
	
	public Account createtheAccount(String first, String last, String email, String usern, String pass) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		Account playerAccount= new Account();
		
		playerAccount.enterFirst(first);
		playerAccount.enterLast(last);
		playerAccount.setEmail(email);
		playerAccount.setUsername(usern); 
		playerAccount.setPassword(pass);
		allUserAccounts.add(playerAccount);
	
		
		return playerAccount;
	}
	
	

	
}
