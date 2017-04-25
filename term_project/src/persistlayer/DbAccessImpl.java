package persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleSequence;
import objectlayer.BringList;
import objectlayer.Party;
import persistlayer.DbAccessConfiguration;

public class DbAccessImpl {
	private static Connection con;
	/**
	 * connection 
	 * @return
	 */
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DbAccessConfiguration.DRIVE_NAME);
			con = DriverManager.getConnection(DbAccessConfiguration.CONNECTION_URL, DbAccessConfiguration.DB_CONNECTION_USERNAME, DbAccessConfiguration.DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	} // end of connect
	/**
	 * retrieves a resultset and returns it
	 * @param query
	 * @return
	 */
	public static ResultSet retrieve (String query, Connection con) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}// end of retrieve
	/**
	 * creates a new value in a table
	 * @param sql
	 * @return
	 */
	public static int create(String sql) {
		Connection c = connect();
		int r = 0;
		try {
			Statement s = c.createStatement();
			r = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect(c);
		}
		return r;
	}
	/**
	 * updates values in the table
	 * @param sql
	 * @return
	 */
	public static int update(String sql){
		Connection c = connect();
		int r = 0;
		try {
			Statement s = c.createStatement();
			r = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect(c);
		}
		return r;
	}
	/**
	 * deletes from the table based on the query
	 * @param sql
	 * @return
	 */
	public static int delete(String sql){
		Connection c = connect();
		int r = 0;
		try {
			Statement s = c.createStatement();
			r = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect(c);
		}
		return r;
	}
	/**
	 * disconnects from a connection
	 * @param con
	 */
	public static void disconnect(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of closeConnection
	
	public static String getString(String sql, String toget) {
		Connection con = connect();
		ResultSet rs = retrieve(sql,con);
		String r = null;
		try {
			if (rs.next())
				r = rs.getString(toget);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect(con);
		return r;	
	}
	
	public static int getInt(String sql, String toget){
		Connection c = connect();
		ResultSet rs = retrieve(sql, c);
		int r = 0;
		try {
			if (rs.next())
				r = rs.getInt(toget);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect(c);
		return r;	
	}
	
	public static SimpleSequence getSequence(String sql, DefaultObjectWrapperBuilder db){
		Connection c = connect();
		ResultSet rs = retrieve(sql, c);
		SimpleSequence sq = new SimpleSequence(db.build());
		try {
			
				while(rs.next()){
					String temp = rs.getString(1);
					sq.add(temp);
				}
			
			}catch (SQLException e){
				e.printStackTrace();
			}
		
		disconnect(c);
		return sq;
		
	}
	
	public static Party getParty(String sql){
		Connection c = connect();
		ResultSet rs = retrieve(sql,c);
		Party p = new Party();
		try {
			if (rs.next()){
				p.setName(rs.getString("name"));
				p.setStime(rs.getString("stime"));
				p.setEtime(rs.getString("etime"));
				p.setDescription(rs.getString("description"));
				p.setLocation(rs.getString("location"));
				p.setHost(rs.getInt("user_id"));
				p.setPartyId(rs.getInt("party_id"));
				if (rs.getInt("public")==1)
					p.setPub(true);
				else 
					p.setPub(false);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public static List<Party> getTrending(String sql){
		Connection c = connect();
		ResultSet rs = retrieve(sql,c);
		List<Party> partys = new ArrayList<Party>();
		int counter = 0;
		try {
			while(rs.next() && counter < 4) {
				Party p = new Party();
				p.setName(rs.getString("name"));
				p.setScore(rs.getInt("score"));
				p.setStime(rs.getString("stime"));
				
				partys.add(p);
				++counter;
			} // end of while
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(c);
		return partys;
	}
	
	public static SimpleSequence getParties(String sql, DefaultObjectWrapperBuilder db){
		Connection c = connect();
		ResultSet rs = retrieve(sql, c);
		SimpleSequence sq = new SimpleSequence(db.build());
		int counter = 0;
		try {
				
				while(rs.next() && counter < 15){
					String temp = rs.getString(1);
					sq.add(temp);
					++counter;
				}
			
			}catch (SQLException e){
				e.printStackTrace();
			}
		
		disconnect(c);
		return sq;
		
	}
	public static List<BringList> getBringList(String sql){
		Connection c = connect();
		ResultSet rs = retrieve(sql,c);
		List<BringList> bl = new ArrayList<BringList>();
		int counter = 0;
		try {
			while(rs.next()) {
				BringList b = new BringList();
				b.setBringlist(rs.getString("bringlist"));
				bl.add(b);
				++counter;
			} // end of while
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(c);
		return bl;
	}
}

