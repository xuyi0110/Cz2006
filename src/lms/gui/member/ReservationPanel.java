package lms.gui.member;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;

import lms.Config;
import lms.entity.Entity;
import lms.entity.Item;
import lms.entity.Reservation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservationPanel extends EntityPanel {
	private Reservation reservation;
	
	private JLabel lblItemTitle;
	private JLabel lblItemReservedDate;
	private JLabel lblItemTotalReservation;
	private JLabel lblItemNumberInQueue;
	private JButton btnCancel;
	
	public ReservationPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblTitle.setBounds(32, 11, 46, 14);
		add(lblTitle);
		
		lblItemTitle = new JLabel("");
		lblItemTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItemTitle.setBounds(133, 0, 284, 25);
		add(lblItemTitle);
		
		JLabel lblReservedDate = new JLabel("Reserved Date:");
		lblReservedDate.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblReservedDate.setBounds(275, 40, 97, 14);
		add(lblReservedDate);
		
		lblItemReservedDate = new JLabel("");
		lblItemReservedDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemReservedDate.setBounds(386, 40, 203, 14);
		add(lblItemReservedDate);
		
		JLabel lblTotalReservation = new JLabel("Total Reservations:");
		lblTotalReservation.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblTotalReservation.setBounds(275, 65, 118, 14);
		add(lblTotalReservation);
		
		lblItemTotalReservation = new JLabel("");
		lblItemTotalReservation.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemTotalReservation.setBounds(386, 65, 204, 14);
		add(lblItemTotalReservation);
		
		JLabel lblNumberInQueue = new JLabel("Position in Queue:");
		lblNumberInQueue.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblNumberInQueue.setBounds(275, 90, 118, 14);
		add(lblNumberInQueue);
		
		lblItemNumberInQueue = new JLabel("");
		lblItemNumberInQueue.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemNumberInQueue.setBounds(386, 90, 145, 14);
		add(lblItemNumberInQueue);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "Do you really want to cancel reservation?";
					int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						accept();
					}
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCancel.setBounds(508, 86, 89, 23);
		add(btnCancel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 115, 600, 2);
		add(separator);
	}

	@Override
	public void setEntity(Entity entity) throws Exception {
		reservation = (Reservation) entity;
		reservation.getInfo();
		Item item = reservation.getItem();
		item.getInfo();
		
		lblItemTitle.setText(item.getTitle());
		lblItemReservedDate.setText(Config.DATE_FORMAT.format(reservation.getReservedDate()));
		lblItemTotalReservation.setText(((Integer) reservation.getQueueSize()).toString());
		lblItemNumberInQueue.setText(((Integer) reservation.getNumberInQueue()).toString());
	}

	@Override
	public void accept() throws Exception {
		frame.getReserveMgr().cancelReservation(reservation);
		setVisible(false);
		EntityPanelContainer.getContainer(this).reducePanelSize();
		getParent().remove(this);
	}
}
