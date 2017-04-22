package persistlayer;
import objectlayer.*;

import java.security.NoSuchAlgorithmException;

import boundary.PasswordHash;
public class ApolloPersistImpl {

	
	public int addUser(User u){
		byte[] salt = null;
		String hpword = null;
		try {
			salt = PasswordHash.getSalt();
			hpword = PasswordHash.hashPass(u.getPassword(), salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO users (first_name,last_name,email,username,password,salt) VALUES" + "('" +u.getFname()+"','" + u.getLname() +"','"+u.getEmail()+ "','"+ u.getUsername() + "','"+ u.getPassword() + "','" +salt +"');" ;;
		return DbAccessImpl.create(sql);
	}
	
	public int signIn(String uname, String pword){
		String sql = "SELECT password FROM users WHERE username=\"" + uname + "\";";
		String sql2 = "SELECT salt FROM users where username=\"" + uname+"\";";
		String r = DbAccessImpl.getPass(sql, "password");
		byte[] s = DbAccessImpl.getSalt(sql2, "salt");
		
		String hpword = null;
		try {
			hpword = PasswordHash.hashPass(pword, s);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pword.equals(r))
			return 1;
		else 
			return 0;
		
	}
	
}
