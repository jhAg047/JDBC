package com.kh.model.service;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

//controller와 dao사이에 있는 service
public class MemberService {

	public int insertMember(Member member) {
		Connection conn = getConnection(); //JDBCTemplate.getConnection() static 메소드 가져올때는 클래스명.메소드명 사용 아니면 위에 import문 작성
		
		MemberDAO mDAO = new MemberDAO();
		// 필드에 안만들고 굳이 메소드에 넣는 이유 
		// 필드에 생성하면 프로퍼티 파일에 변경이 일어났을때 변경이 불가해서 
		
		int result = mDAO.insertMember(conn, member); // conn dao에서 커넥션을 사용하기 위해 넘김  -> dao가 해야하는 일은 sql 실행만 해야하니까 서비스를 분리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		ArrayList<Member>mList = mDAO.selectAll(conn);
		
		return mList;
	}

	public ArrayList<Member> selectMemberId(String id) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectMemberId(conn,id);
		
		return mList;
	}

	public ArrayList<Member> selectGender(char gender) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectGender(conn,gender);
		
		return mList;
	}
	
	
}
