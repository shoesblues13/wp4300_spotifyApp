package persistlayer;
import objectlayer.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

import boundary.PasswordHash;
public class ApolloPersistImpl {

	
	public int addUser(User u){
		
		
		String sql = "INSERT INTO users (first_name,last_name,email,username,password) VALUES" + "('" +u.getFname()+"','" + u.getLname() +"','"+u.getEmail()+ "','"+ u.getUsername() + "','"+ u.getPassword() + "');" ;;
		return DbAccessImpl.create(sql);
	}
	
	public int signIn(String uname, String pword){
		String sql = "SELECT password FROM users WHERE username=\"" + uname + "\";";
		String sql2 = "SELECT user_id FROM users where username=\"" + uname + "\";";
		int user_id = DbAccessImpl.getInt(sql2, "user_id");
		String r = DbAccessImpl.getString(sql, "password");
		if (pword.equals(r))
			return user_id;
		else 
			return 0;
		
	}
	
	public String getName(String uname) {
		String sql = "SELECT first_name FROM users WHERE username=\"" + uname + "\";";
		String r = DbAccessImpl.getString(sql, "first_name");
		return r;
		
	}
	
	public int addParty(Party p) {
		String sql = "INSERT into party  (name,stime,etime,description,location,public,score,status) VALUES('"+p.getName()+"','"+p.getStime()+"','"+p.getEtime()+"','"+p.getDescription()+"','"+p.getLocation()+"','"+p.getPub()+"','"+p.getScore()+"','"+p.getStatus() + "');"; 
		return DbAccessImpl.create(sql);
	}
	
	public ResultSet getParties(String uname){
		String sql = "SELECT name FROM party WHERE user_id = (SELECT user_id FROM users WHERE uname = '"+uname+"')";
		return DbAccessImpl.retrieve(sql);
	}
	
	public ResultSet getUserInvited(String uname){
		String sql = "SELECT name FROM party WHERE user_id = (SELECT user_id FROM users WHERE uname = '"+uname+"')";
		return DbAccessImpl.retrieve(sql);
	}
}
