package project02.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class ConnectionFactory {
	
	private static DataSource datasource = null;
	static {
		try {
			Context context = new InitialContext();
			// The name of the data source is 'UsersDB' as listed in content.xml
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/csci370db");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection con, Statement st, ResultSet rs) throws Exception {
		if(con!=null)
            con.close();
		if(st!=null)
            st.close();
		if(rs!=null)
            rs.close();
	}
	
	public static DataSource getDatasource() {
		return datasource;
	}
	
	public static void testConnect() {
		Connection connection = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select Host, User from user");
            int cnt = 1;
            while (rs.next()) {
                System.out.println (
                		cnt +". Host:" + rs.getString("Host") + " User:"+rs.getString("User"));
                cnt++;
            }
            rs.close();
            st.close();
          } catch (SQLException e) {
        	  
			e.printStackTrace();
		} finally {
            if (connection!=null) {
            		try { connection.close(); }
            		catch (Exception ignore) { }
            }
		}
	}
}