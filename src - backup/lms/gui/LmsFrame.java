package lms.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import lms.Config;
import lms.entity.Admin;
import lms.entity.Member;
import lms.gui.admin.AdminPanel;
import lms.gui.member.EntityPanelContainer;
import lms.gui.member.LoanPanel;
import lms.gui.member.LoginPanel;
import lms.gui.member.ReservationPanel;
import lms.gui.member.ShelfItemPanel;
import lms.gui.member.UserPanel;
import lms.gui.search.MainSearchPanel;
import lms.gui.search.SearchPanel;
import lms.manager.*;

public class LmsFrame extends JFrame{
	
	private LoginMgr loginMgr;
	private RenewMgr renewMgr;
	private ReserveMgr reserveMgr;
	private ShelfMgr shelfMgr;
	private BorrowMgr borrowMgr;
	private SearchMgr searchMgr;
	private ItemMgr itemMgr;
	private MemberMgr memberMgr;
	private CheckOutMgr checkOutMgr;
	private ReturnMgr returnMgr;
	
	private LoginPanel loginPanel;
	private UserPanel userPanel;
	private JPanel cards;
	private SearchPanel searchPanel;
	private MainSearchPanel mainSearchPanel;
	private AdminPanel adminPanel;
	
	private JMenuBar memberMenuBar;
	private JMenuItem mntmStatus;
	private JMenuItem mntmLogOut;
	private JMenuItem mntmViewBorrowed;
	private JMenuItem mntmViewReservations;
	private JMenuItem mntmViewMyShelf;
	
	private JMenuBar adminMenuBar;
	
	public LmsFrame() {
		setBounds(250, 100, 800, 520);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Library Management System");
		getContentPane().setLayout(new CardLayout());
		
		
		JPanel memberPanel = new JPanel();
		memberPanel.setLayout(new BorderLayout());
		
		loginMgr = new LoginMgr();
		renewMgr = new RenewMgr();
		reserveMgr = new ReserveMgr();
		shelfMgr = new ShelfMgr();
		borrowMgr = new BorrowMgr();
		searchMgr = new SearchMgr();
		itemMgr = new ItemMgr();
		memberMgr = new MemberMgr();
		checkOutMgr = new CheckOutMgr();
		returnMgr = new ReturnMgr();
		
		
		loginPanel = new LoginPanel();
		userPanel = new UserPanel();
		mainSearchPanel = new MainSearchPanel();
		
		
		cards = new JPanel(new CardLayout());
		
		searchPanel = new SearchPanel();
		memberPanel.add(searchPanel, BorderLayout.PAGE_START);
		
		cards.add(loginPanel, "loginPanel");
		cards.add(userPanel, "userPanel");
		cards.add(mainSearchPanel, "mainSearchPanel");
		
		memberPanel.add(cards, BorderLayout.CENTER);
		
		getContentPane().add(memberPanel, "memberPanel");
		
		
		adminPanel = new AdminPanel();
		getContentPane().add(adminPanel, "adminPanel");
		createMemberMenuBar();
		createAdminMenuBar();
		setJMenuBar(memberMenuBar);
		
		
		
		
		setVisible(true);
		initialize();
	}
	
	public void createAdminMenuBar() {
		adminMenuBar = new JMenuBar();
		JMenu mnNavi = new JMenu("Navi");
		adminMenuBar.add(mnNavi);
		mnNavi.setMnemonic(KeyEvent.VK_N);
		
		JMenuItem mntmNavi = new JMenuItem("Navi");
		mntmNavi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminPanel.changePanel("adminNaviPanel");
			}
		});
		mnNavi.add(mntmNavi);
		
		JSeparator separatorAdmin = new JSeparator();
		mnNavi.add(separatorAdmin);
		JMenuItem mntmLogOut = new JMenuItem("Log out ...");
		mntmLogOut.setMnemonic(KeyEvent.VK_L);
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Do you really want to logout?";
				int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loginMgr.logout();
					changeMemberPanel("loginPanel");
					changeDomain("memberPanel");
					menuRefresh();
				}
			}
		});
		mnNavi.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("Exit ...");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure to exit?";
				int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnNavi.add(mntmExit);
		
		JMenu mnMngItems = new JMenu("Mng Items");
		adminMenuBar.add(mnMngItems);
		
		JMenuItem mntmAddItem = new JMenuItem("Add Item");
		mntmAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("addItemSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Add Item");
			}
		});
		mnMngItems.add(mntmAddItem);
		
		JMenuItem mntmUpdateItem = new JMenuItem("Update Item");
		mntmUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("updateItemSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Update Item");
			}
		});
		mnMngItems.add(mntmUpdateItem);
		
		JMenuItem mntmDeleteItem = new JMenuItem("Delete Item");
		mntmDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("deleteItemSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Delete Item");
			}
		});
		mnMngItems.add(mntmDeleteItem);
		
		JMenu mnMngMembers = new JMenu("Mng Members");
		adminMenuBar.add(mnMngMembers);
		
		JMenuItem mntmAddMember = new JMenuItem("Add Member");
		mntmAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("addMemberSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Add Member");
			}
		});
		mnMngMembers.add(mntmAddMember);
		
		JMenuItem mntmUpdateMember = new JMenuItem("Update Member");
		mntmUpdateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("updateMemberSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Update Member");
			}
		});
		mnMngMembers.add(mntmUpdateMember);
		
		JMenuItem mntmDeleteMember = new JMenuItem("Delete Member");
		mntmDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("deleteMemberSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Delete Member");
			}
		});
		mnMngMembers.add(mntmDeleteMember);
		
		JMenu mnOperation = new JMenu("Operation");
		adminMenuBar.add(mnOperation);
		
		JMenuItem mntmCheckOut = new JMenuItem("Check Out");
		mntmCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("checkOutSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Check Out");
			}
		});
		mnOperation.add(mntmCheckOut);
		
		JMenuItem mntmReturn = new JMenuItem("Return");
		mntmReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("returnSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Return");
			}
		});
		mnOperation.add(mntmReturn);
	}
	public void createMemberMenuBar() {
		memberMenuBar = new JMenuBar();
		
		
		JMenu mnProfile = new JMenu("Profile");
		memberMenuBar.add(mnProfile);
		mnProfile.setMnemonic(KeyEvent.VK_P);
		
		mntmStatus = new JMenuItem("Status");
		mntmStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					userPanel.updateProfile();
					userPanel.changePanel("statusPanel");
					changeMemberPanel("userPanel");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		mnProfile.add(mntmStatus);
		
		JSeparator separator = new JSeparator();
		mnProfile.add(separator);
		
		mntmLogOut = new JMenuItem("Log in ...");
		mntmLogOut.setMnemonic(KeyEvent.VK_L);
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginMgr.hasUser()) {
					String message = "Do you really want to logout?";
					int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loginMgr.logout();
						changeMemberPanel("loginPanel");
						menuRefresh();
					}
				}
				else {
					changeMemberPanel("loginPanel");
				}
			}
		});
		mnProfile.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("Exit ...");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure to exit?";
				int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnProfile.add(mntmExit);
		
		JMenu mnBorrow = new JMenu("Borrow");
		memberMenuBar.add(mnBorrow);
		
		mntmViewBorrowed = new JMenuItem("View Borrowed Items");
		mntmViewBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EntityPanelContainer entitiesPanel = userPanel.getEntityPanelContainer();
					entitiesPanel.setEntityPanelSize(650, 125);
					entitiesPanel.setPanelName("Books Holding");
					entitiesPanel.setPanels(renewMgr.getLoanList((Member)loginMgr.getUser()), LoanPanel.class);
					userPanel.changePanel("entityPanelContainer");
					changeMemberPanel("userPanel");
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBorrow.add(mntmViewBorrowed);
		
		JMenu mnReserve = new JMenu("Reserve");
		memberMenuBar.add(mnReserve);
		
		mntmViewReservations = new JMenuItem("View Reservations");
		mntmViewReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EntityPanelContainer entitiesPanel = userPanel.getEntityPanelContainer();
					entitiesPanel.setEntityPanelSize(650, 125);
					entitiesPanel.setPanelName("Books Reserving");
					entitiesPanel.setPanels(reserveMgr.viewAllReservations((Member)loginMgr.getUser()), ReservationPanel.class);
					userPanel.changePanel("entityPanelContainer");
					changeMemberPanel("userPanel");
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnReserve.add(mntmViewReservations);
		
		JMenu mnMyshelf = new JMenu("MyShelf");
		memberMenuBar.add(mnMyshelf);
		
		mntmViewMyShelf = new JMenuItem("View My Shelf");
		mntmViewMyShelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EntityPanelContainer entitiesPanel = userPanel.getEntityPanelContainer();
					entitiesPanel.setEntityPanelSize(650, 125);
					entitiesPanel.setPanelName("Books on My Shelf");
					entitiesPanel.setPanels(shelfMgr.getShelfItems((Member)loginMgr.getUser()), ShelfItemPanel.class);
					userPanel.changePanel("entityPanelContainer");
					changeMemberPanel("userPanel");
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		mnMyshelf.add(mntmViewMyShelf);
		
		menuRefresh();
		
	}
	
	public LoginMgr getLoginMgr() {
		return loginMgr;
	}
	
	public RenewMgr getRenewMgr() {
		return renewMgr;
	}
	
	public ReserveMgr getReserveMgr() {
		return reserveMgr;
	}
	
	public ShelfMgr getShelfMgr() {
		return shelfMgr;
	}
	
	public BorrowMgr getBorrowMgr() {
		return borrowMgr;
	}
	
	public SearchMgr getSearchMgr() {
		return searchMgr;
	}
	
	public ItemMgr getItemMgr() {
		return itemMgr;
	}
	
	public MemberMgr getMemberMgr() {
		return memberMgr;
	}
	
	public CheckOutMgr getCheckOutMgr() {
		return checkOutMgr;
	}
	
	public ReturnMgr getReturnMgr() {
		return returnMgr;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public UserPanel getUserPanel() {
		return userPanel;
	}
	
	public MainSearchPanel getMainSearchPanel() {
		return mainSearchPanel;
	}
	
	public AdminPanel getAdminPanel() {
		return adminPanel;
	}
	
	
	
	private void initialize() {
		userPanel.init();
		loginPanel.init();
		searchPanel.init();
		mainSearchPanel.init();
		adminPanel.init();
		
	}
	
	public void changeMemberPanel(String panel) {
		CardLayout cardLayout = (CardLayout)(cards.getLayout());
		cardLayout.show(cards, panel);
	}
	
	public void changeDomain(String domain) {
		CardLayout cardLayout = (CardLayout)(getContentPane().getLayout());
		cardLayout.show(getContentPane(), domain);
	}
	
	public static LmsFrame GetLmsFrame(Container container) {
		Container parentContainer = container.getParent();
		while (parentContainer != null) {
			if (parentContainer instanceof LmsFrame) 
				return (LmsFrame) parentContainer;
			parentContainer = parentContainer.getParent();
		}
		return (LmsFrame) parentContainer;
	}
	public void menuRefresh() {
		if (loginMgr.hasUser() && (loginMgr.getUser() instanceof Admin)) {
			setJMenuBar(adminMenuBar);
		}
		else {
			setJMenuBar(memberMenuBar);
			memberMenuRefresh();
		}
	}
	public void memberMenuRefresh() {
		boolean hasUser = loginMgr.hasUser();
		mntmStatus.setEnabled(hasUser);
		mntmViewBorrowed.setEnabled(hasUser);
		mntmViewReservations.setEnabled(hasUser);
		mntmViewMyShelf.setEnabled(hasUser);
		if (hasUser) {
			mntmLogOut.setText("Log out ...");
		}
		else {
			mntmLogOut.setText("Log in ...");
		}
	}
}
