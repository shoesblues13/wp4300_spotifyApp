package objectlayer;

public class User {
	
	private String fname;
	private String lname;
	private String uname;
	private String pword;
	private String email;
	
	
	public User(String fname, String lname, String email, String username, String password){
		this.fname = fname;
		this.lname = lname;
		this.uname = username;
		this.pword = password;
		this.email = email;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getUsername() {
		return uname;
	}


	public void setUsername(String username) {
		this.uname = username;
	}


	public String getPassword() {
		return pword;
	}


	public void setPassword(String password) {
		this.pword = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
