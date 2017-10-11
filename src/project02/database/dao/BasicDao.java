package project02.database.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import project02.database.ConnectionFactory;

public abstract class BasicDao {
	final static DataSource ds = ConnectionFactory.getDatasource();
	
	protected Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
