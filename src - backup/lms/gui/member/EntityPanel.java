package lms.gui.member;

import javax.swing.JPanel;

import lms.entity.Entity;
import lms.gui.LmsFrame;

public abstract class EntityPanel extends JPanel {
	protected LmsFrame frame;
	public abstract void setEntity(Entity entity) throws Exception;
	public abstract void accept() throws Exception;
	public void init() {
		frame = LmsFrame.GetLmsFrame(this);
	}
	
}
