package persistlayer;
import objectlayer.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
	public int getUserId(String uname) {
		String sql = "SELECT user_id FROM users WHERE username=\"" + uname + "\";";
		int r = DbAccessImpl.getInt(sql, "user_id");
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
	
	public SimpleSequence getParties(int uname, DefaultObjectWrapperBuilder db){
		String sql = "SELECT name FROM party WHERE user_id =\""+uname+"\";";
		return DbAccessImpl.getParties(sql, db);
	}
	
	public SimpleSequence getUserInvited(int uname, DefaultObjectWrapperBuilder db){
		String sql = "SELECT party.name FROM party INNER JOIN guestlist ON party.party_id = guestlist.party_id AND guestlist.user_id =\"" + uname + "\";";
		return DbAccessImpl.getSequence(sql, db);
	}
	
	public Party getParty(int party_id){
		
		String sql = "SELECT * FROM party where party_id =\"" + party_id +"\";";;
		return DbAccessImpl.getParty(sql);
	}
	
	public SimpleSequence getGuestList(int party_id, DefaultObjectWrapperBuilder db){
		String sql = "SELECT guestname FROM guestlist where party_id=\"" + party_id + "\";";
		return DbAccessImpl.getSequence(sql, db);
	}
	
	public List<Party> getTrending(){
		String sql = "SELECT * from party;";
		return DbAccessImpl.getTrending(sql);
	}
	
	public SimpleSequence getMusicList(int party_id, DefaultObjectWrapperBuilder db){
		String sql = "SELECT songList FROM music WHERE party_id ='"+party_id+"';";
		return DbAccessImpl.getSequence(sql, db);
	}
	public SimpleSequence getBringList(int party_id, DefaultObjectWrapperBuilder db){
		String sql = "SELECT bringlist FROM bringlist WHERE party_id = '"+party_id+"';";
		return DbAccessImpl.getSequence(sql, db);
	}
	
	public List<BringList> getBringList(int party_id){
		String sql = "SELECT bringlist FROM bringlist WHERE party_id = '"+party_id+"';";
		return DbAccessImpl.getBringList(sql);
	}
	
	public int addBringList(String bringListInput, int party_id){
		String sql = "INSERT INTO bringList (bringlist, party_id) VALUES('"+bringListInput+"',' "+party_id+"');";
		return DbAccessImpl.create(sql);
	}
	
	public int addMusicList(String musicListInput, int party_id){
		String sql = "INSERT INTO music (songList, party_id) VALUES('"+musicListInput+"',' "+party_id+"');";
		return DbAccessImpl.create(sql);
	}
	public SimpleSequence getPartyIds(int uname, DefaultObjectWrapperBuilder db){
		String sql = "SELECT party_id FROM party WHERE user_id =\""+uname+"\";";
		return DbAccessImpl.getSequence(sql, db);
	}
	public int addGuestList(String guestListInput, int party_id){
		String name = getName(guestListInput);
		int id = getUserId(guestListInput);
		if(id >0 && name !=null){
			String sql = "INSERT INTO guestlist (guestname, party_id, user_id) VALUES('"+name+"', '"+party_id+"', '"+ id+"');";
			return DbAccessImpl.create(sql);
		}
		return -1;
	}
	
	public Party getParty(String name, int user_id){
		String sql = "SELECT party_id FROM party WHERE name=\""+name+"\" AND user_id=\"" +user_id + "\";";
		int party_id = DbAccessImpl.getInt(sql, "party_id");
		Party p = getParty(party_id);
		return p;
	}
	
	public SimpleSequence getPublicPartys(DefaultObjectWrapperBuilder db){
		String sql = "SELECT name FROM party where public=\"" + 1+"\";";
		return DbAccessImpl.getSequence(sql, db);
	}
	
}