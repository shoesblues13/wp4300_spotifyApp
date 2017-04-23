package persistlayer;
import objectlayer.*;

import java.security.NoSuchAlgorithmException;

import boundary.PasswordHash;
public class ApolloPersistImpl {

	
	public int addUser(User u){
		
		
		String sql = "INSERT INTO users (first_name,last_name,email,username,password,salt) VALUES" + "('" +u.getFname()+"','" + u.getLname() +"','"+u.getEmail()+ "','"+ u.getUsername() + "','"+ u.getPassword() + "');" ;;
		return DbAccessImpl.create(sql);
	}
	
	public int signIn(String uname, String pword){
		String sql = "SELECT password FROM users WHERE username=\"" + uname + "\";";
		String r = DbAccessImpl.getString(sql, "password");
		if (pword.equals(r))
			return 1;
		else 
			return 0;
		
	}
	
	public String getName(String uname) {
		String sql = "SELECT first_name FROM users WHERE username=\"" + uname + "\";";
		String r = DbAccessImpl.getString(sql, "first_name");
		return r;
		
	}
	
	public int addParty(Party p) {
		String sql = "INSTER into party  (name,stime,etime,description,location,public,score,status) VALUES('"+p.getName()+"','"+p.getStime()+"','"+p.getEtime()+"','"+p.getDescription()+"','"+p.getLocation()+"','"+p.getPub()+"','"+p.getScore()+"','"+p.getStatus() + "');"; 
		return DbAccessImpl.create(sql);
	}
	
}
