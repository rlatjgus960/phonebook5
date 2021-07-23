package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	// 전체리스트 가져오기
	public List<PersonVo> getPersonList() {

		// db요청
		// 리스트 가져오기
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList"); //mybatis한테 시킨거!!
		//System.out.println("dao");
		//System.out.println(personList);
		

		return personList;
	}
	
	//전화번호 저장
	public int personInsert(PersonVo personVo) {
		
		int count = sqlSession.insert("phonebook.personInsert", personVo); //인서트문에서 써야하므로 같이 넘겨줌
		
		return count;
	}
}