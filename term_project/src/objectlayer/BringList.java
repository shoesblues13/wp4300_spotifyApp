package objectlayer;

public class BringList {
	private int bring_id;
	private String bringlist;
	private int party_id;

	public BringList(){
		
	}
	
	public BringList(String bringList, int party_id){
		this.bringlist = bringList;
		this.party_id = party_id;
	}
	
	public int getBring_id() {
		return bring_id;
	}
	/**
	 * @param bring_id the bring_id to set
	 */
	public void setBring_id(int bring_id) {
		this.bring_id = bring_id;
	}
	/**
	 * @return the bringlist
	 */
	public String getBringlist() {
		return bringlist;
	}
	/**
	 * @param bringlist the bringlist to set
	 */
	public void setBringlist(String bringlist) {
		this.bringlist = bringlist;
	}
	/**
	 * @return the party_id
	 */
	public int getParty_id() {
		return party_id;
	}
	/**
	 * @param party_id the party_id to set
	 */
	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}
	
}
