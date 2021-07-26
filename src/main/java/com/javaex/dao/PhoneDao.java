package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList"); // mybatis한테 시킨거!!
		// System.out.println("dao");
		// System.out.println(personList);

		return personList;
	}

	// 전화번호 저장
	public int personInsert(PersonVo personVo) {

		int count = sqlSession.insert("phonebook.personInsert", personVo); // 인서트문에서 써야하므로 같이 넘겨줌

		return count;
	}

	public int personInsert2(String name, String hp, String company) {

		System.out.println("[PhoneDao.personInsert2]");

		// PersonVo personVo = new PersonVo();
		// 딱맞는게 없으면
		// 방법1 : 만들어준다
		// 근데 vo만들어봤자 어차피 한번만 쓸것같음(만들어도 되긴 함)
		// 방법2 : map을 사용해서 데이터를 묶는다.

		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap);
		int count = sqlSession.insert("phonebook.personInsert2", personMap);
		System.out.println("dao.personInsert2 결과 "+count);

		return count;
	}

	// 전화번호 삭제
	public int personDelete(int personId) {

		int count = sqlSession.delete("phonebook.personDelete", personId);

		return count;
	}

	// 전화번호 수정폼, 전화번호 1개 정보 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("[PhoneDao.getPerson()]");
		System.out.println(personId);
		PersonVo personVo = sqlSession.selectOne("phonebook.selectPerson", personId);
		System.out.println(personVo);
		return personVo;
	}
	
	// 전화번호 수정폼2, 전화번호 1개 정보 가져오기
	public Map<String, Object> getPerson2(int personId) {
		System.out.println("[PhoneDao.getPerson2()]");
		System.out.println(personId);
		
		// PersonVo personVo = new PersonVo();
		// 딱맞는게 없으면
		// 방법1 : 만들어준다
		// 근데 vo만들어봤자 어차피 한번만 쓸것같음(만들어도 되긴 함)
		// 방법2 : map을 사용해서 데이터를 묶는다.
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectPerson2", personId);
		System.out.println(personMap);
		System.out.println(personMap.get("PERSON_ID")); //키값으로 컬럼명의 대문자형이 들어감
		
		return personMap;
	}

	// 전화번호 수정
	public int personUpdate(PersonVo personVo) {

		int count = sqlSession.update("phonebook.personUpdate", personVo);

		return count;
	}

	// 전화번호 수정2
	public int personUpdate2(int personId, String name, String hp, String company) {
		
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("personId", personId);
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);

		int count = sqlSession.update("phonebook.updatePerson2", personMap);

		return count;
	}

}