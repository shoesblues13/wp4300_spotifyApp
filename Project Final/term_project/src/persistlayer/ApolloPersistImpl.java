package persistlayer;
import objectlayer.*;
public class ApolloPersistImpl {

	
	public int addUser(User u){
		String sql = "INSERT INTO users (first_name,last_name,email,username,password) VALUES" + "('" +u.getFname()+"','" + u.getLname() +"','"+u.getEmail()+ "','"+ u.getUsername() + "','"+ u.getPassword() +"');" ;;
		return DbAccessImpl.create(sql);
	}
	
	public int signIn(String uname, String pword){
		String sql = "SELECT password FROM users WHERE username=\"" + uname + "\"";
		return -1;
	}
	
}
