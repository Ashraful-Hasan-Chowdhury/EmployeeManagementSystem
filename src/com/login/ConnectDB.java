package com.login;
import java.sql.*;
import java.sql.Connection;

import javax.swing.JOptionPane;

public class ConnectDB {
	Connection conn=null;
	public static Connection dbConnector()
	{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root",
					"");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Database not connected");
			return null;
		}
	}
}
