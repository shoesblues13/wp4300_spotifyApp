package persistlayer;
import objectlayer.*;

import java.security.NoSuchAlgorithmException;

import boundary.PasswordHash;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleSequence;
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
		int pub = 0;
		if(p.getPub()){
			pub = 1;
		}
		String sql = "INSERT into party  (name,stime,etime,description,location,public, user_id) VALUES('"+p.getName()+"','"+p.getStime()+"','"+p.getEtime()+"','"+p.getDescription()+"','"+p.getLocation()+"','"+pub+"','"+p.getHost() + "');"; 
		DbAccessImpl.create(sql);
		String sql2 = "SELECT party_id FROM party where name=\"" + p.getName() + "\";";
		int party_id = DbAccessImpl.getInt(sql2, "party_id");
		return party_id;
	}
	
	public SimpleSequence getParties(String uname, DefaultObjectWrapperBuilder db){
		String sql = "SELECT name FROM party WHERE user_id = (SELECT user_id FROM users WHERE uname = '"+uname+"')";
		return DbAccessImpl.getSequence(sql, db);
	}
	
	public SimpleSequence getUserInvited(String uname, DefaultObjectWrapperBuilder db){
		String sql = "SELECT name FROM party WHERE user_id = (SELECT user_id FROM users WHERE uname = '"+uname+"')";
		return DbAccessImpl.getSequence(sql, db);
	}
	
	
}
