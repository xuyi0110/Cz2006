package lms.gui.admin;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lms.Config;
import lms.gui.LmsFrame;
import lms.gui.member.EntityPanelContainer;

public class AdminNaviPanel extends JPanel{
	private LmsFrame frame;
	
	public AdminNaviPanel() {
		setLayout(null);
		
		JLabel lblManageItems = new JLabel("Manage Items:");
		lblManageItems.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblManageItems.setBounds(94, 43, 116, 28);
		add(lblManageItems);
		
		JLabel lblManageMember = new JLabel("Manage Member:");
		lblManageMember.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblManageMember.setBounds(94, 157, 142, 28);
		add(lblManageMember);
		
		JLabel lblOperation = new JLabel("Operation:");
		lblOperation.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblOperation.setBounds(94, 287, 116, 28);
		add(lblOperation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(94, 138, 534, 8);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(94, 268, 534, 8);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(94, 391, 534, 8);
		add(separator_2);
		
		JButton btnItemAdd = new JButton("Add");
		btnItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("addItemSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Add Item");
			}
		});
		btnItemAdd.setBounds(147, 104, 89, 23);
		add(btnItemAdd);
		
		JButton btnItemUpdate = new JButton("Update");
		btnItemUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("updateItemSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Update Item");
			}
		});
		btnItemUpdate.setBounds(311, 104, 89, 23);
		add(btnItemUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("deleteItemSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Delete Item");
			}
		});
		btnDelete.setBounds(474, 104, 89, 23);
		add(btnDelete);
		
		JButton btnMemberAdd = new JButton("Add");
		btnMemberAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("addMemberSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Add Member");
			}
		});
		btnMemberAdd.setBounds(147, 230, 89, 23);
		add(btnMemberAdd);
		
		JButton btnMemberUpdate = new JButton("Update");
		btnMemberUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("updateMemberSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Update Member");
			}
		});
		btnMemberUpdate.setBounds(311, 230, 89, 23);
		add(btnMemberUpdate);
		
		JButton btnMemberDelete = new JButton("Delete");
		btnMemberDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("deleteMemberSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Delete Member");
			}
		});
		btnMemberDelete.setBounds(474, 230, 89, 23);
		add(btnMemberDelete);
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AdminPanel adminPanel = frame.getAdminPanel();
					EntityPanelContainer entityPanelContainer = adminPanel.getAdminManagementPanel().getCheckOutSearchPanel().getEntityPanelContainer();
					entityPanelContainer.setPanelName("Loans");
					entityPanelContainer.setEntityPanelSize(525, 110);
					entityPanelContainer.setPanels(frame.getCheckOutMgr().getAllRequestList(), LoanRequestPanel.class);
					adminPanel.changePanel("adminManagementPanel");
					adminPanel.getAdminManagementPanel().changePanel("checkOutSearchPanel");
					adminPanel.getAdminManagementPanel().setPanelName("Check Out");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCheckOut.setBounds(208, 357, 101, 23);
		add(btnCheckOut);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel adminPanel = frame.getAdminPanel();
				adminPanel.changePanel("adminManagementPanel");
				adminPanel.getAdminManagementPanel().changePanel("returnSearchPanel");
				adminPanel.getAdminManagementPanel().setPanelName("Return");
			}
		});
		btnReturn.setBounds(417, 357, 89, 23);
		add(btnReturn);
	}
	
	public void init() {
		frame = (LmsFrame) LmsFrame.GetLmsFrame(this);
	}
}
