package logiclayer;

import persistlayer.ApolloPersistImpl;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleSequence;
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
	
	public int createParty(String name, String timeStart, String stime, String etime,String description, Boolean pub, int hostId) {
		Party p = new Party(name,timeStart,stime,etime,description,pub,hostId);
		return apolloPersist.addParty(p);
	}
	
	public SimpleSequence getParties(String uname, DefaultObjectWrapperBuilder db){
		return apolloPersist.getParties(uname, db);
	}
	
	public SimpleSequence getUserInvited(String uname, DefaultObjectWrapperBuilder db){
		return apolloPersist.getParties(uname, db);
	}
	
	public int getParty(int party_id){
		return apolloPersist.getParty(party_id);
	}
	
	
	
}
