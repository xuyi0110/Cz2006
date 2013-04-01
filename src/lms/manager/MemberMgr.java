package lms.manager;

import java.sql.SQLException;

import lms.entity.Member;

public class MemberMgr {
	
	public boolean checkMemberExist(String mid) throws Exception {
		Member member = new Member(mid);
		return member.getInfo();
	}
	
	public Member getMember(String id) throws Exception {
		Member member = new Member(id);
		member.getInfo();
		return member;
	}
	
	public void addMember(Member member) throws Exception {
		member.updateIntoDataBase();
	}
	
	public void deleteMember(Member member) throws Exception {
		member.deleteFromDataBase();
		member.deleteFromAccount();
	}
	
	public void addAccount(Member member, String password) throws SQLException {
		member.updateIntoAccount(password);
		
	}

}
