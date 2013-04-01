package lms.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import lms.Config;

public abstract class Entity {
	
	protected Statement st;
	
	public Entity() {
	}
	
	protected void DBconnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(Config.DATABASE_URL, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);
			st = con.createStatement();
		}catch(Exception ex){
			System.out.println("Erorr: "+ex);
		}
	}
	
	public abstract boolean getInfo() throws Exception;
	
	public abstract void updateIntoDataBase() throws Exception;

}
