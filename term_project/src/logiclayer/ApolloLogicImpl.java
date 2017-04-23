package logiclayer;

import persistlayer.ApolloPersistImpl;
import objectlayer.*;

public class ApolloLogicImpl {
	ApolloPersistImpl apolloPersist;

	public ApolloLogicImpl() {
		
		apolloPersist = new ApolloPersistImpl();
	}
	
	public int createUser(String fname, String lname, String email, String uname, String pword) {
		User u = new User(fname,lname,email,uname,pword);
		return apolloPersist.addUser(u);
	}
	
	public int signIn(String uname, String pword){
		return apolloPersist.signIn(uname,pword);
	}
	
	public String getName(String uname) {
		return apolloPersist.getName(uname);
	}
	
	public int createParty(String name, String host, String stime, String etime,String description, String location, Boolean pub, int score, String key, String status ) {
		Party p = new Party(name,host,stime,etime,description,location,pub,score,key,status);
		return apolloPersist.addParty(p);
	}
	
	
	
	
}
