package lms.entity;

import java.sql.ResultSet;

public abstract class User extends Entity{
	
	protected String id;
	protected String name;
	
	public User() {
		super();
	}
	
	public User(String id) {
		super();
		this.id = id;
	}
	
	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getPswd() throws Exception{
		DBconnect();
		ResultSet rs;
		String query = "select * from account where mID = '" + id +"';";
			
		rs = st.executeQuery(query);
		
		//System.out.println("Record from database");
		while(rs.next()){
			String password = rs.getString("password");
			return password;
		}
		if (st != null) {
			st.close();
		}
		throw new Exception("Username not Exist");
	}
	
	//GET id of ...
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
