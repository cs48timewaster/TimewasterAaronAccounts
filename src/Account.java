import java.io.Serializable;


public class Account implements Serializable{

	public Account() {
		// TODO Auto-generated constructor stub
	}
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String username;
	private String password;
	
	
	
	public void enterFirst(String first){
		firstName=first;
	}
	
	public void enterLast(String last){
		lastName=last;
	}
	public String getName(){
		System.out.println(firstName+ lastName);
		return firstName + " " + lastName;
	}
	
	public void setEmail(String email){
		emailAddress=email;
	}
	public String getEmail(){
		return emailAddress;
	}
	
	public void setUsername(String usern){
		username=usern;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setPassword (String pass){
		password=pass;
	}
	
	public String getPassword(){
		return password;
	}

}
