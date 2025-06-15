package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadoProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbExeceptions(e.getMessage());
			}

		}
		return conn;
	}

	public static void closeConnection() {
		try {

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DbExeceptions(e.getMessage());
		}
	}

	private static Properties loadoProperties() {
		try (FileInputStream fs = new FileInputStream("db.propeties")) {
			Properties proper = new Properties();
			proper.load(fs);
			return proper;
		} catch (IOException e) {
			throw new DbExeceptions(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			//fecha o objeto do tipo statement já com a tratação da exceção para que n seja tratada toda vez
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbExeceptions(e.getMessage());
			}
		}
		
	}
	public static void resultSet(ResultSet rs) {
		//fecha o objeto do tipo ResultSet já com a tratação da exceção para que n seja tratada toda vez
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbExeceptions(e.getMessage());
			}
		}
		
	}

}
