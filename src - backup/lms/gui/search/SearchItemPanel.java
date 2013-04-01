package lms.gui.search;

import lms.entity.Entity;
import lms.entity.Item;
import lms.entity.Reservation;
import lms.gui.LmsFrame;
import lms.gui.member.EntityPanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchItemPanel extends EntityPanel {
	private LmsFrame frame;
	
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblIsbn;
	
	private Item item;
	private JSeparator separator;
	private JSeparator separator_1;
	
	public SearchItemPanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					accept();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		setLayout(null);
		
		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTitle.setBounds(143, 35, 198, 27);
		add(lblTitle);
		
		lblAuthor = new JLabel("");
		lblAuthor.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblAuthor.setBounds(143, 101, 145, 14);
		add(lblAuthor);
		
		lblIsbn = new JLabel("");
		lblIsbn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblIsbn.setBounds(143, 145, 145, 14);
		add(lblIsbn);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(10, 0, 2, 220);
		add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 195, 402, 2);
		add(separator_1);
	}

	@Override
	public void setEntity(Entity entity) throws Exception {
		item = (Item) entity;
		if (!item.getInfo())
			throw new Exception("Item not Exist");
		lblTitle.setText(item.getTitle());
		lblAuthor.setText(item.getAuthor());
		lblIsbn.setText(item.getIsbn());
		
	}

	@Override
	public void accept() throws Exception {
		if (frame == null) 
			System.out.println("frame is null");
		frame.getMainSearchPanel().getItemDetailPanel().setItem(item);
		frame.getMainSearchPanel().ChangePanel("ItemDetailPanel");
	}
	
	public void init() {
		frame = LmsFrame.GetLmsFrame(this);
	}
}
