/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database_Connector {
    
    public Connection connection;
    public Statement stm;
    public ResultSet rs;
    
    public Connection getConnection(){          
		                    
		System.out.println("Connection called");                  
		try {                                                                   
			Class.forName("com.mysql.jdbc.Driver");              
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms","admin", "khuljasimsim9955");
    	 	
		} catch (ClassNotFoundException | SQLException e) {
                    System.out.println("Error: " + e);
		}
		return connection;
	}
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void executeSQL(String SQL) {
        try {
            stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
}
