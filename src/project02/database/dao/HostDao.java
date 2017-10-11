package project02.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project02.database.HostInfo;

public class HostDao extends BasicDao {
	public HostInfo getCurrentHost() {
		HostInfo hostInfo = null;
		try(
				Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT CURRENT_USER() AS user, NOW() AS time");
				ResultSet resultSet = statement.executeQuery();
				) {
			resultSet.next();
			String name = resultSet.getString("user");
			String time = resultSet.getString("time");
			hostInfo = new HostInfo(name, time);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return hostInfo;		
	}

}
