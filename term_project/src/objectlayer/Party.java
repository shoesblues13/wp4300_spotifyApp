package objectlayer;

public class Party {
	
	private String name;
	private int host;
	private String stime;
	private String description;
	private String location;
	private Boolean pub;
	
	private int score;
	private String key;
	private String etime;
	private String status;
	private int party_id;
	
	public Party(){
		
	}
	public Party(String name, String stime, String etime,String description, String location, Boolean pub, int hostId){
		this.name = name;
		this.host = hostId;
		this.description = description;
		this.stime = stime;
		this.etime = etime;
		this.location = location;
		this.pub = pub;


		
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getPub() {
		return pub;
	}
	public void setPub(Boolean pub) {
		this.pub = pub;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHost() {
		return host;
	}
	public void setHost(int host) {
		this.host = host;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setPartyId(int party_id){
		this.party_id= party_id;
	}
	public int getPartyId(){
		return this.party_id;
	}
}
