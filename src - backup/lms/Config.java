package lms;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Config {
	//Dialog infomation
	public static String TITLE_SUCCESS = "Success"; 
	public static String TITLE_ERROR = "ERROR";
	public static String TITLE_WARNING = "WARNING";
	
	//DataBase 
	public static String DATABASE_URL = "jdbc:mysql://localhost:3306/cz2006db";
	public static String DATABASE_USERNAME = "root";
	public static String DATABASE_PASSWORD = "root";
	
	// Member Part Constant
	public static int STUDENT_QUOTA = 2;
	public static int STUFF_QUOTA = 3;
	
	public static int STUDENT_RENEW_LIMIT = 3;
	public static int STUFF_RENEW_LIMIT =6;
	
	//Loan Part Constant
	public static int STUDENT_LOAN_DURATION = 7;
	public static int STUFF_LOAN_DURATION = 14;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static int FINE = 100;
	
	
	//Panel
	public static Color BEIGE = new Color(234, 224, 205);
	

}
