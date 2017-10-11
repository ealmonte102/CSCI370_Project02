package project02.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project02.database.BasicFile;

public class FileDao extends  BasicDao {
	public boolean insertFile(BasicFile fileToInsert) {
		try(
			Connection con = getConnection();
			PreparedStatement statement = createInsertStatement(con, fileToInsert);
		) { 
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public List<BasicFile> getFiles() {
		List<BasicFile> files = new ArrayList<BasicFile>();
		try(
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT name, extension FROM file");
			ResultSet resultSet = statement.executeQuery();
		) { 
			while(resultSet.next()) {
				String filename = resultSet.getString("name");
				String extension = resultSet.getString("extension");
				files.add(new BasicFile(filename + "." + extension));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return files;
	}

	private PreparedStatement createInsertStatement(Connection con, BasicFile fileToInsert) throws SQLException {
		PreparedStatement statement = con.prepareStatement("INSERT INTO file (name, extension) VALUES (?, ?)");
		statement.setString(1, fileToInsert.getFilename());
		statement.setString(2, fileToInsert.getExtension());
		return statement;
	}
}
