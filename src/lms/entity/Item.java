package lms.entity;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import lms.Config;

public class Item extends Entity{
	
	private String isbn;
	private String title;
	private String author;
	private int quantity;
	private String description;
	private String type;
	private ImageIcon bookImage;
	private ImageIcon thumbnailBookImage;
	
	private int noOutstanding;
	private int noReserved;
	private int noRequesting;
	
	public Item(){
		super();
	}
	
	public Item(String isbn) {
		super();
		this.isbn = isbn;
	}
	
	public Item(String isbn, String title, String author, int quantity) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		
	}

	@Override
	public boolean getInfo() throws Exception{
		DBconnect();
		ResultSet rs;
		String query = "select * from item where isbn = '"+isbn+"';";
		rs = st.executeQuery(query);
	
		while(rs.next()) {
			this.title = rs.getString("title");
			this.author = rs.getString("author");
			this.quantity = rs.getInt("quantity");
			this.description = rs.getString("description");
			this.type = rs.getString("type");
			
			InputStream byte_stream = rs.getBinaryStream("image");
			if(byte_stream.available()>0)
			{
				Image image = ImageIO.read(byte_stream);
				this.bookImage =  new ImageIcon(Config.changeSize(image, Config.IMAGE_WIDTH,Config.IMAGE_HIGHT ) );
				this.thumbnailBookImage = new ImageIcon(Config.changeSize(image, (Config.IMAGE_WIDTH)/2,(Config.IMAGE_HIGHT)/2 ) );
			}
			else 
			{
				this.bookImage = Config.DEFAULT_IMAGE;
				this.bookImage = Config.THUMBNAIL_DEFAULT_IMAGE;
			}
			this.noRequesting = this.noOutstanding = this.noReserved = 0;
			
			query = "select * from outstanding_loan where isbn = '"+isbn+"';";
			ResultSet newResultSet = st.executeQuery(query);
			while (newResultSet.next()) {
				this.noOutstanding ++;
			}
			
			query = "select * from loan_request where isbn = '"+isbn+"';";
			newResultSet = st.executeQuery(query);
			while (newResultSet.next()) {
				this.noRequesting ++;
			}
			
			query = "select * from reservation where isbn = '"+isbn+"';";
			newResultSet = st.executeQuery(query);
			while (newResultSet.next()) {
				this.noReserved ++;
			}
			st.close();
			return true;
		}
		if (st != null)
			st.close();
		return false;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getFreeQty() {
		return quantity - noOutstanding - noRequesting;
	}
	
	public void setAuthor(String author) {
		this.author = author;	
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description; 
	}
	
	public void setBookImage(ImageIcon image){
		this.bookImage = image;
	}
	
	public ImageIcon getBookImage(){
		return this.bookImage;
	}
	
	public ImageIcon getThumbnailBookImage(){
		return this.thumbnailBookImage;
	}
	
	public boolean isAvailable() {
		return (getFreeQty()>0);
	}
	
	@Override
	public void updateIntoDataBase() throws SQLException {
		deleteFromDataBase();
		DBconnect();
		st.executeUpdate("INSERT INTO item "+"VALUES('"+ isbn +"','" + type +  "','" + title + "','" + author + "','" + quantity + "','" + description +"');");
		st.close();
	}
	
	public void deleteFromDataBase() throws SQLException {
		DBconnect();
		String query = "DELETE FROM item WHERE isbn ='"+ isbn + "';";
		st.execute(query);
		st.close();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitile(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getNoOutstanding() {
		return noOutstanding;
	}
	
	public int getNoReserved() {
		return noReserved;
	}
	
	public int getNoRequesting() {
		return noRequesting;
	}
	
}

