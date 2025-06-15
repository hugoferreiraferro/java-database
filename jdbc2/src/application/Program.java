package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			// cria uma conexão com a database
			conn = DB.getConnection();
			
			st = conn.createStatement(); //objeto statement instanciado
			rs = st.executeQuery("select * from department");
			while(rs.next()) {
				//chama um tipo int passando o NOME da coluna, fazendo a mesma coisa com a String.
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.resultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection(); 
		}

	}

}
