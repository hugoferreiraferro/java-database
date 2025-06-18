package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbExeceptions;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();

			conn.setAutoCommit(false);
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE departmentId = 1");

			int x = 1;
			//if (x < 2) {
			//	throw new SQLException("FAKE ERROR");
			//}
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 4090 WHERE departmentId = 2");
			conn.commit();
			System.out.println("Rows1 " + rows1);
			System.out.println("Rows2 " + rows2);
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbExeceptions("Transaction rolled back Caused by: " + e.getMessage());
			} catch (SQLException e1) { 
				throw new DbExeceptions("Error trying to rollback Caused by: " + e1.getMessage());
			}
		}

	}

}
