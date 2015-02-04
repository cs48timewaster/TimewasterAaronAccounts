import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


//http://www.java2s.com/Code/Java/Swing-JFC/Createamainmenu.htm
	
	
public class mainMenu extends JPanel implements ActionListener {
  
	JPanel panel;
	protected static ArrayList<Account> allUserAccounts = new ArrayList<Account>();
	
	mainMenu() {
    JFrame f = new JFrame("Welcome to the Timewaster");
    f.setSize(550, 500);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar jmb = new JMenuBar();
    
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setBackground(Color.GREEN);
    
    JLabel creatAccount= new JLabel("Create an Account");
    JLabel forgotPassword= new JLabel("Forgot PAssword?");
    JLabel signIn= new JLabel("Sign in");
    
    GridBagConstraints c = new GridBagConstraints();
    c.gridwidth = GridBagConstraints.REMAINDER;
    
    c.fill = GridBagConstraints.HORIZONTAL;
   
    
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 4.0;
    c.weighty = 4.0;
    new GridBagLayout();
    
     //panel.add(signIn,c);

    JMenu jmFile = new JMenu("File");
    JMenuItem jmiOpen = new JMenuItem("Open");
    JMenuItem jmiClose = new JMenuItem("Close");
    JMenuItem jmiSave = new JMenuItem("Save");
    JMenuItem jmiExit = new JMenuItem("Exit");
    jmFile.add(jmiOpen);
    jmFile.add(jmiClose);
    jmFile.add(jmiSave);
    jmFile.addSeparator();
    jmFile.add(jmiExit);
    jmb.add(jmFile);

    JMenu jmOptions = new JMenu("Options");
    JMenu a = new JMenu("A");
    JMenuItem b = new JMenuItem("B");
    
    JMenuItem d = new JMenuItem("D");
    a.add(b);
   
    a.add(d);
    jmOptions.add(a);

    JMenu e = new JMenu("E");
    e.add(new JMenuItem("F"));
    e.add(new JMenuItem("G"));
    jmOptions.add(e);

    jmb.add(jmOptions);

    JMenu jmHelp = new JMenu("Help");
    JMenuItem jmiAbout = new JMenuItem("About");
    jmHelp.add(jmiAbout);
    jmb.add(jmHelp);

    jmiOpen.addActionListener(this);
    jmiClose.addActionListener(this);
    jmiSave.addActionListener(this);
    jmiExit.addActionListener(this);
    b.addActionListener(this);
   
    d.addActionListener(this);
    jmiAbout.addActionListener(this);

    f.setJMenuBar(jmb);
    f.setVisible(true);
    
    JLabel welcome = new JLabel ("Welcome to Timewaster!!", SwingConstants.CENTER);
    JLabel please = new JLabel("Please select an option below",SwingConstants.CENTER);
   
    
    Font labelFont = welcome.getFont();
    String labelText = welcome.getText();

    int stringWidth = welcome.getFontMetrics(labelFont).stringWidth(labelText);
    int componentWidth = welcome.getWidth();
    
 // Find out how much the font can grow in width.
    double widthRatio = (double)componentWidth / (double)stringWidth;

    int newFontSize = (int)(labelFont.getSize() * widthRatio);
    int componentHeight = welcome.getHeight();

    // Pick a new font size so it will not be larger than the height of label.
    int fontSizeToUse = Math.min(newFontSize, componentHeight)-1;

    // Set the label's font size to the newly determined size.
    Font ff= new Font("Courier",Font.PLAIN, 32);
    welcome.setFont(ff);
    welcome.setForeground(Color.BLUE);
 //   welcome.setText("Please select an option below");
    panel.add(welcome,c);
    

     labelFont = please.getFont();
     labelText = please.getText();

     stringWidth = please.getFontMetrics(labelFont).stringWidth(labelText);
     componentWidth = please.getWidth();
    
 // Find out how much the font can grow in width.
    
   
    // Set the label's font size to the newly determined size.
     ff= new Font("Serif",Font.PLAIN, 22);
     
    please.setFont(ff);
    please.setForeground(Color.BLUE);
 //   welcome.setText("Please select an option below");
    panel.add(please,c);
    
    JButton signINButton = new JButton("Sign In");
    panel.add(signINButton, c);
    signINButton.addActionListener(this);
    
    JButton signinGuest= new JButton("Sign In as Guest");
    panel.add(signinGuest,c);
    signinGuest.addActionListener(this);
    
    
    
    JButton createAccount = new JButton("Create an account");
    panel.add( createAccount,c);
    createAccount.addActionListener(new ActionListener(){
    	@Override
    	public void actionPerformed(ActionEvent e){
    		 SwingUtilities.invokeLater(new Runnable() {

 				@Override
 				public void run() {
 					// TODO Auto-generated method stub
 					try {
						new createAccount();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 				}
 				});
    	}
    });
    
    JButton forgotPass= new JButton("Forgot Password?");
    panel.add(forgotPass, c);
   
   forgotPass.addActionListener(new ActionListener(){
    	@Override
    	public void actionPerformed(ActionEvent ee){
    		 SwingUtilities.invokeLater(new Runnable() {

 				@Override
 				public void run() {
 					// TODO Auto-generated method stub
 					try {
						new forgotPassword();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 				}
 				});
    	}
    });
    
    
    f.add( panel);
    f.setVisible(true);
  }
  public void actionPerformed(ActionEvent ae) {
    String comStr = ae.getActionCommand();
    System.out.println(comStr + " Selected");
  }
  public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException {
	  ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("userinfo.txt"));
  	allUserAccounts = (ArrayList<Account>) fileIn.readObject();
  	fileIn.close();
  	
  	
    new mainMenu();
    
    
  	  	    	  FileOutputStream fos = new FileOutputStream("userinfo.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(allUserAccounts);
		oos.close();
  }
}
