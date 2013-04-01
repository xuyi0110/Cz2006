package lms.manager;

import lms.entity.User;

import java.lang.reflect.Constructor;


public class LoginMgr {
	
	private User user;
	public LoginMgr(){		
		user = null;
	}
	
	
	public void register() {
		
	}
	
	public void login(String domain, String username, String password) throws Exception{
		Class<?> c = Class.forName("lms.entity."+domain);
		Constructor<?> cons = c.getConstructor(String.class);
		user = (User)cons.newInstance(username);
		
		String userPassword = user.getPswd();
		if (! password.equals(userPassword)) {
			throw new Exception("Wrong Password");
		}
		
		if (!user.getInfo())
			throw new Exception("username not exist");
	}

	public void logout() {
		user = null;
	}
	
	public boolean hasUser() {
		return user!=null;
	}
	
	public User getUser() {
		return user;
	}
}

