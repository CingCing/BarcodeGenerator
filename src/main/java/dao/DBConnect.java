/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.QrCodeComponent;


/**
 * @author Cong
 *
 */
public class DBConnect {
	static QrCodeComponent qrcode = new QrCodeComponent();
	public static Connection getConnection() throws SQLException {		
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
		         "databaseName=BarcodeStored;user=sa;password=1234";
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void insert(String text) throws SQLException {
		Connection con=getConnection();  
		Statement statement = con.createStatement();	
				
		String sql = "insert into BarcodeStored.dbo.qrText values('" + text + "')";	
		statement.executeUpdate(sql);	
	}
}
