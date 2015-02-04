import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Container.*;
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

import java.util.*;

import javax.activation.*;

import java.util.Properties;  

import javax.mail.*;  
import javax.mail.internet.*;

public class forgotPassword extends mainMenu implements ActionListener{

	
	 JFrame          fpFrame;
    JPanel          fpPanel;
    JTextField		recoverEmail;
  private  JLabel	notifyLabel;
  private  String 			recoverEmailString;
    
    public void setEmailString(String k){
    	recoverEmailString=k;
    }
    public String getEmailString(){
    	return recoverEmailString;
    }
    
    
	
	public forgotPassword() throws FileNotFoundException, IOException, ClassNotFoundException {
		//super ();
		//frame.setVisible(false);
        fpPanel = new JPanel();
        fpPanel.setLayout(new GridBagLayout());
        fpPanel.setBackground(Color.BLUE);
        fpFrame = new JFrame("Recover your password");
        fpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fpFrame.getContentPane().add(BorderLayout.CENTER, fpPanel);
        
        JLabel emailRecoverLabel= new JLabel("Enter your E-mail:");
        new GridBagLayout();

        
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        
 
        
	       GridBagConstraints right = new GridBagConstraints();
	        right.anchor = GridBagConstraints.WEST;
	        GridBagConstraints left = new GridBagConstraints();
	        left.anchor = GridBagConstraints.EAST;

	        right.weightx = (int) 3;
	        right.fill = GridBagConstraints.REMAINDER;
	        right.gridwidth = GridBagConstraints.REMAINDER;
	        
	        fpPanel.add(emailRecoverLabel,left);
	        
	        recoverEmail= new JTextField(10);
	        recoverEmail.addActionListener(this);
	        fpPanel.add(recoverEmail, right);
		        recoverEmail.selectAll();
	        
		        
		        
		        
	        JButton retrievePass = new JButton("Retrieve your password ");
	        fpFrame.getContentPane().add(BorderLayout.SOUTH, retrievePass);
	        retrievePass.addActionListener(this);
	        

	         notifyLabel = new JLabel();
	        fpFrame.getContentPane().add(BorderLayout.NORTH, notifyLabel);

	        fpFrame.setSize(300, 250);
	        fpFrame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
String theEmailString= recoverEmail.getText();

notifyLabel = new JLabel();
fpFrame.getContentPane().add(BorderLayout.NORTH, notifyLabel);
	Boolean checkemailAvailable=false;
	   Iterator<Account> iter= allUserAccounts.iterator();
	   Account checkthisAccount;
	   while (iter.hasNext()){
		   checkthisAccount=iter.next();
		   if(checkthisAccount.getEmail().equals(theEmailString)){
			   try {
				this.sendEmail(theEmailString);
			} catch (AddressException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   notifyLabel.setText("An email has been sent containing your password");
			   checkemailAvailable=true;
			   fpFrame.setVisible(false);
			  
		   }
		    
	   }
	  if(!checkemailAvailable) notifyLabel.setText("This E-mail address is not in our records");
	 /* try {
		this.sendEmail(theEmailString);
	} catch (AddressException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (MessagingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
}

	
	
	public void sendEmail(String emailtoSend) throws AddressException, MessagingException{
		
		Account getAccount = null;
		Iterator<Account> iter= allUserAccounts.iterator();
		   Account checkthisAccount;
		   while (iter.hasNext()){
			   checkthisAccount=iter.next();
			   if(checkthisAccount.getEmail().equals(emailtoSend)){
		getAccount=checkthisAccount;
	}
		   }
		   
		   String emailBody= "Hello" + getAccount.getName() + "\n" + "Your username and password for the Timewaster login is " +
		   getAccount.getUsername() + " Password " + getAccount.getPassword();
		   
		    Properties mailServerProperties;
			 Session getMailSession;
			 MimeMessage generateMailMessage;
		     
		    	
		    	//code is borrowed from Javapoint.com and crunchify.com
		   System.out.println("\n 1st ===> setup Mail Server Properties.." + emailtoSend);
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			System.out.println("Mail Server Properties have been setup successfully..");
	 
	//Step2		
			System.out.println("\n\n 2nd ===> get Mail Session..");
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailtoSend));
			generateMailMessage.setSubject("Timewaster Account");
			generateMailMessage.setContent(emailBody, "text/html");
			System.out.println("Mail Session has been created successfully..");
	 
	//Step3		
			System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");
			
			// Enter your correct gmail UserID and Password (XXXa.shah@gmail.com)
			transport.connect("smtp.gmail.com", "ahmedbinalsadat@gmail.com", "nigger22");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		
	
		     
		     //Get the session object  
		    /* Properties props = new Properties();  
		     props.put("mail.smtp.host", "smtp.gmail.com");  
		     props.put("mail.smtp.socketFactory.port", "465");  
		     props.put("mail.smtp.socketFactory.class",  
		               "javax.net.ssl.SSLSocketFactory");  
		     props.put("mail.smtp.auth", "true");  
		     props.put("mail.smtp.port", "465");  
		      
		     Session session = Session.getDefaultInstance(props,  
		      new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		      return new PasswordAuthentication("ahmedbinalsadat@gmail.com","nigger22");//change accordingly  
		      }  
		     });  
		      
		     //compose message  
		     try {  
		      MimeMessage message = new MimeMessage(session);  
		      message.setFrom(new InternetAddress("ahmedbinalsadat@gmail.com"));//change accordingly  
		      message.addRecipient(Message.RecipientType.TO,new InternetAddress("aaronzakhor@gmail.com"));  
		      message.setSubject("Important Information for Timewaster Account");  
		      message.setText("Wassup");  
		        
		      //send message  
		      Transport.send(message);  
		     
		      System.out.println("message sent successfully");  
		      
		     } catch (MessagingException e) {throw new RuntimeException(e);}  
		      
		 */   }  
		     
		   //public static void main(String args[]){
		
		   
}
	
	

	
  

