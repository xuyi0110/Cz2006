package lms.entity;

import java.sql.*;

public class Admin extends User{
	
	public Admin() {
		super();
		
	}
	
	public Admin(String id) {
		super(id);
	}
	
	@Override
	public boolean getInfo() throws Exception{
		DBconnect();
		ResultSet rs;
		String query = "select * from admin where ID = '" + id +"';";
		
		rs = st.executeQuery(query);
		
		while(rs.next()){			
			this.name = rs.getString("name");
			return true;
		}
		if (st != null) {
			st.close();
		}
		return false;
	}

	@Override
	public void updateIntoDataBase() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
