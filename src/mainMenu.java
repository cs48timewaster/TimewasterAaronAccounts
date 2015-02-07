package loginScreen;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class mainMenu extends JPanel implements ActionListener {
	protected boolean disposed=false;
	
	private JPanel panel;
	public JFrame mainJFrame;
	public JDialog dialog;
	private JMenu jmFile;
	private JMenuBar jMenuBar;
	private GridBagConstraints gbc;
	
	
	protected boolean signedInAsUser=false;
	protected static boolean signedInAsGuest=true;
	
	protected  void setUserSignedInStatus(boolean value){
		signedInAsUser=value;
	}
	protected static void setSignedInAsGuest(boolean value){
		signedInAsGuest=value;
	}
	
	public void addJMIOpensToJMenu(String name){
		JMenuItem jmiOpen = new JMenuItem(name);
		jmiOpen.addActionListener(this);
		jmFile.add(jmiOpen);
	}
	public void addJMenuBarFileTab(){
		jmFile = new JMenu("File");
		addJMIOpensToJMenu("Open");
		addJMIOpensToJMenu("Close");
		addJMIOpensToJMenu("Save");
		addJMIOpensToJMenu("Exit");	
		jMenuBar.add(jmFile);
	}
	public void addJMenuBarHelpTab(){
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmiAbout.addActionListener(this);
		jmHelp.add(jmiAbout);
		jMenuBar.add(jmHelp);

	}
	public void initializeJMenuBar(){
		jMenuBar = new JMenuBar();
		addJMenuBarFileTab();
		addJMenuBarHelpTab();
	}
	public void setGridBagConstraints(){
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 5.0;
		gbc.weighty = 5.0;
	}
	public void addMainActionButton(String title){
		JButton signINButton = new JButton(title);
		panel.add(signINButton, gbc);
		signINButton.addActionListener(this);
	}
	public void addMainScreenButtonsToPanel(){
		addMainActionButton("Sign In");
		addMainActionButton("Sign in as Guest");
		addMainActionButton("Create an account");
		addMainActionButton("Forgot Password?");
	}
	public void addIntroTextToPanel(){
		JLabel welcome = new JLabel("Welcome to Timewaster!!",SwingConstants.CENTER);
		JLabel please = new JLabel("Please select an option below",SwingConstants.CENTER);
		Font ff = new Font("Courier", Font.PLAIN, 32);
		welcome.setFont(ff);
		welcome.setForeground(Color.BLUE);
		panel.add(welcome, gbc);
		
		ff = new Font("Serif", Font.PLAIN, 22);
		please.setFont(ff);
		please.setForeground(Color.BLUE);
		panel.add(please, gbc);
	}
	public void initializeJPanel(){
		panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setLayout(new GridBagLayout());
		setGridBagConstraints();
	}
	public void initializeViewMainJFrame(){
		mainJFrame = new JFrame("hurry....");
		mainJFrame.setSize(550, 500);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setJMenuBar(jMenuBar);
		mainJFrame.add(panel);
		mainJFrame.setVisible(true);
	}
	Timer timer;
	public void initializeTimer(){
		 timer = new Timer(200, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(signedInAsUser) {
					mainJFrame.setVisible(false);
					disposed=true;
					timer.stop();
				}
			}
		});
		timer.start(); 
	}
	
	
	public void continueUntilFrameClosed(JFrame checkThisFrame){
	boolean mainMenuDisposed=false;
	do{
		if(checkThisFrame.isVisible()) continue;
		else {
			checkThisFrame.dispose();
			mainMenuDisposed=true;
		}
	} while(!mainMenuDisposed);
}


	public mainMenu() {
		
		Account Ludlow = new Account("Christopher","Ludlow","chrisaludlow@gmail.com","cal000lud","password");
		AccountInventory.allUserAccounts.put("chrisaludlow@gmail.com",Ludlow);
		
		
		initializeJPanel();
		addIntroTextToPanel();
		addMainScreenButtonsToPanel();
		initializeJMenuBar();
		initializeViewMainJFrame(); //adds the panel and the menubar and sets it to visible
		dialog = new JDialog(mainJFrame);
		initializeTimer();
		continueUntilFrameClosed(mainJFrame);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(refactorTheseGlobalVariables.otherWindowOpen==true) return;
		String comStr = ae.getActionCommand();
		if(comStr.equals("Sign In")){
			new SignInAsUser(this);
		}
		else if(comStr.equals("Forgot Password?")){
			new forgotPassword();
		}
		else if(comStr.equals("Create an account")){
			new createAccount();
		}
	}

}
