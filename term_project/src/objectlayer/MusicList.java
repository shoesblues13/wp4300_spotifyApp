package objectlayer;

public class MusicList {
	private int music_id;
	private String songList;
	private int party_id;

	public MusicList(String songList, int party_id){
		this.songList = songList;
		this.party_id = party_id;
		
		
		
		
		
	}

	/**
	 * @return the music_id
	 */
	public int getMusic_id() {
		return music_id;
	}

	/**
	 * @param music_id the music_id to set
	 */
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	/**
	 * @return the songList
	 */
	public String getSongList() {
		return songList;
	}

	/**
	 * @param songList the songList to set
	 */
	public void setSongList(String songList) {
		this.songList = songList;
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
