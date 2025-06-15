package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			
			conn = DB.getConnection();
			/*
			st = conn.prepareStatement( // comandos básicos SQL
					"INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId)" + "VALUES"
							+ "(?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS); // placeholders que indicam onde o valor sera colocado
			st.setString(1, "Carl Purple");
			st.setString(2, "Carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // para mandar para o SQL é necessário
																					// """converter"""
			st.setDouble(4, 3000.00);
			st.setInt(5, 4); //atenção o 4 é o id do departamento, o id é colocado automaticamente
			*/
			st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')", // d1 e d2 são os nomes que serão adicionados na coluna name
					Statement.RETURN_GENERATED_KEYS);
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1); //indica o valor da primeira coluna
					System.out.println("Done! id = " + id);
				}
			}
			else {
				System.out.println("No rows affected");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}  finally {
			DB.closeStatement(st);
			DB.closeConnection(); // sempre fechar a conexão por último
		}

	}

}
