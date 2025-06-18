package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			st.setInt(1, 8);
			int rowsAffect = st.executeUpdate();
			
			System.out.println("DONE: rows affected: " + rowsAffect);
		}
		catch (Exception e) {
			throw new DbIntegrityException(e.getMessage());
		}
 	

	}

}
