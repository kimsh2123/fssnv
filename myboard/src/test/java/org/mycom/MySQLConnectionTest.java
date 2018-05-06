package org.mycom;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

/*
 * Connection TEST
 * Run as > JUnit Test 로 실행
 */

public class MySQLConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/javatest";
	private static final String USER = "javauser";
	private static final String PW = "javadude";
	
	@Test
	public void testConnection() throws Exception{
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			
			System.out.println(con);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
