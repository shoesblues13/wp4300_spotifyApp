package logiclayer;

import persistlayer.ApolloPersistImpl;

import java.util.List;

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
	
	public SimpleSequence getParties(int uname, DefaultObjectWrapperBuilder db){
		return apolloPersist.getParties(uname, db);
	}
	
	public SimpleSequence getUserInvited(int uname, DefaultObjectWrapperBuilder db){
		return apolloPersist.getUserInvited(uname, db);
	}
	
	public Party getParty(int party_id){
		return apolloPersist.getParty(party_id);
	}
	
	public SimpleSequence getGuestList(int party_id, DefaultObjectWrapperBuilder db){
		return apolloPersist.getGuestList(party_id, db);
	}
	
	public List<Party> getTrending(){
		return apolloPersist.getTrending();
	}
	
	public SimpleSequence getMusicList(int party_id, DefaultObjectWrapperBuilder db){
		return apolloPersist.getMusicList(party_id, db);
	}
	
	public SimpleSequence getBringList(int party_id, DefaultObjectWrapperBuilder db){
		return apolloPersist.getBringList(party_id, db);
	}
	public int addBringList(String bringListInput, int party_id){
		return apolloPersist.addBringList(bringListInput, party_id);
	}
	
	public int addMusicList(String musicListInput, int party_id){
		return apolloPersist.addMusicList(musicListInput, party_id);
	}
	
	
}