package lms.gui.search;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.Icon;

import lms.Config;
import lms.entity.Item;
import lms.entity.Member;
import lms.entity.Reservation;
import lms.gui.LmsFrame;
import lms.gui.member.EntityPanelContainer;
import lms.gui.member.ShelfItemPanel;
import lms.gui.member.UserPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemDetailPanel extends JPanel{
	private Item item;
	private LmsFrame frame;
	
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblIsbn;
	private JLabel lblCopyNumber;
	private JLabel lblIsAvailabel;
	private JLabel lblReservedNumber;
	private JLabel lblImage;
	
	private JButton btnReserve;
	private JButton btnBorrow;
	private JButton btnMyShelf;
	private final JButton btnAddToCart;
	private final JButton btnCheckOut;
	
	public ItemDetailPanel() {
		setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(198, 11, 2, 373);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(584, 11, 2, 373);
		add(separator_1);
		
		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTitle.setBounds(13, 262, 175, 27);
		add(lblTitle);
		
		lblAuthor = new JLabel("");
		lblAuthor.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblAuthor.setBounds(27, 313, 145, 14);
		add(lblAuthor);
		
		lblIsbn = new JLabel("");
		lblIsbn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblIsbn.setBounds(27, 346, 145, 14);
		add(lblIsbn);
		
		JLabel lblTotalCopy = new JLabel("Total Copy");
		lblTotalCopy.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTotalCopy.setBounds(244, 63, 125, 27);
		add(lblTotalCopy);
		
		JLabel lblAvailable = new JLabel("Available:");
		lblAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblAvailable.setBounds(244, 112, 125, 27);
		add(lblAvailable);
		
		JLabel lblReserved = new JLabel("Reserved:");
		lblReserved.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblReserved.setBounds(244, 156, 125, 27);
		add(lblReserved);
		
		lblCopyNumber = new JLabel("");
		lblCopyNumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCopyNumber.setBounds(385, 70, 86, 14);
		add(lblCopyNumber);
		
		lblIsAvailabel = new JLabel("");
		lblIsAvailabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblIsAvailabel.setBounds(385, 119, 86, 14);
		add(lblIsAvailabel);
		
		lblReservedNumber = new JLabel("");
		lblReservedNumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblReservedNumber.setBounds(385, 163, 86, 14);
		add(lblReservedNumber);
		
		lblImage = new JLabel("");
		lblImage.setBounds(45,37,Config.IMAGE_WIDTH,Config.IMAGE_HIGHT);
		add(lblImage);
		
		btnReserve = new JButton("Reserve");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Member member = (Member) frame.getLoginMgr().getUser();
					frame.getReserveMgr().reserve(member, item);
					btnReserve.setText("Reserved");
					btnReserve.setEnabled(false);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnReserve.setBounds(227, 262, 89, 23);
		add(btnReserve);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Member member = (Member) frame.getLoginMgr().getUser(); 
					frame.getBorrowMgr().borrow(member, item);
					JOptionPane.showMessageDialog(null, "loan request has forwarded to check out counter", Config.TITLE_SUCCESS, JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnBorrow.setBounds(466, 262, 89, 23);
		add(btnBorrow);
		
		btnMyShelf = new JButton("Add to My Shelf");
		btnMyShelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Member member = (Member) frame.getLoginMgr().getUser(); 
					frame.getShelfMgr().addItemToShelf(member, item);
					btnMyShelf.setText("In My Shelf");
					btnMyShelf.setEnabled(false);
					btnAddToCart.setEnabled(false);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnMyShelf.setBounds(326, 262, 128, 23);
		add(btnMyShelf);
		

		
		ImageIcon shoppingCart = new ImageIcon("res/shopping_cart.png");
		btnAddToCart = new JButton(shoppingCart);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Member member = (Member) frame.getLoginMgr().getUser(); 
					frame.getShelfMgr().addItemToShelf(member, item);
					btnAddToCart.setEnabled(false);
					btnMyShelf.setEnabled(false);
					UserPanel userPanel = frame.getUserPanel();
					EntityPanelContainer entitiesPanel = userPanel.getEntityPanelContainer();
					entitiesPanel.setPanelName("Books on My Shelf");
					entitiesPanel.setPanels(frame.getShelfMgr().getShelfItems((Member)frame.getLoginMgr().getUser()), ShelfItemPanel.class);
					userPanel.changePanel("entityPanelContainer");
					frame.changeMemberPanel("userPanel");
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnAddToCart.setToolTipText("Add to My Shelf");
		btnAddToCart.setBounds(593, 22, 175, 142);
		btnAddToCart.setContentAreaFilled(false);
		add(btnAddToCart);
		
		ImageIcon checkOut = new ImageIcon("res/check_out.png");
		btnCheckOut = new JButton(checkOut);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = "Do you really want to borrow and logout?";
					int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						Member member = (Member) frame.getLoginMgr().getUser(); 
						frame.getBorrowMgr().borrow(member, item);
						JOptionPane.showMessageDialog(null, "loan request has forwarded to check out counter", Config.TITLE_SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						frame.getLoginMgr().logout();
						frame.changeMemberPanel("loginPanel");
						frame.menuRefresh();
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnCheckOut.setContentAreaFilled(false);
		btnCheckOut.setBounds(593, 187, 175, 142);
		add(btnCheckOut);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getMainSearchPanel().ChangePanel("searchItemContainer");
			}
		});
		btnBack.setBounds(603, 346, 89, 23);
		add(btnBack);
		
	}
	
	public void setItem(Item item) throws Exception {
		this.item = item;
		if (!item.getInfo()) 
			throw new Exception("item not found");
		lblTitle.setText(item.getTitle());
		lblAuthor.setText(item.getAuthor());
		lblIsbn.setText(item.getIsbn());
		lblImage.setIcon(item.getBookImage());
		lblCopyNumber.setText(((Integer) item.getQuantity()).toString());
		if (item.getFreeQty() > 0)
			lblIsAvailabel.setText("Yes");
		else
			lblIsAvailabel.setText("No");
		Reservation reservation = new Reservation(item);
		reservation.getQueueInfo();
		lblReservedNumber.setText(((Integer) reservation.getQueueSize()).toString());
		boolean reserved = true;
		boolean hasUser = frame.getLoginMgr().hasUser();
		btnCheckOut.setEnabled(hasUser);
		if (hasUser)
			reserved = frame.getReserveMgr().checkReserved((Member)frame.getLoginMgr().getUser(), item);
		
		btnReserve.setEnabled(!reserved);
		if (reserved)
			btnReserve.setText("Reserved");
		else btnReserve.setText("Reserve");
		boolean inMyShelf = true;
		if (hasUser)
			inMyShelf = frame.getShelfMgr().checkInShelf((Member)frame.getLoginMgr().getUser(), item);
		btnMyShelf.setEnabled(!inMyShelf);
		btnAddToCart.setEnabled(!inMyShelf);
		if (inMyShelf) 
			btnMyShelf.setText("In My Shelf");
		else btnMyShelf.setText("Add to My Shelf");
		btnBorrow.setEnabled(hasUser);
	}
	public void init() {
		frame = LmsFrame.GetLmsFrame(this);
	}
}
