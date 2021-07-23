package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드
	@Autowired
	private PhoneDao phoneDao;
	// 생성자
	// 메소드-gs
	// 메소드-일반
	
	//리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao();    @Autowired
		
		//Dao의 메소드로 데이터 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();
		
		
		//model 담기 (택배박스에 담기)
		model.addAttribute("personList", personList);
		
		return "/WEB-INF/views/list.jsp";
	}

	
	
	//쓰기폼
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST} )
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	

	//쓰기
	// @ModelAttribute 일때
   @RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
   public String write(@ModelAttribute PersonVo personVo) {
      System.out.println("[PhoneController.write]");

      // @ModelAttribute -> new ParsonVo 
      //                ->e 기본생성자 + setter

      System.out.println(personVo);
      
      //Dao 사용
      //PhoneDao phoneDao = new PhoneDao();
      
      //Dao의 personInsert() 이용해서 데이터 저장
      int count = phoneDao.personInsert(personVo);
   
      //view --> 리다이렉트
      return "redirect:/list";
   }

   
   
	
	@RequestMapping(value="/board/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable("no") int boardNo) {
		System.out.println("PathVariable [read]");
		
		//localhost:8088/phonebook5/board/read/1
		//localhost:8088/phonebook5/board/read/2
		//localhost:8088/phonebook5/board/read/100
		
		System.out.println(boardNo);
		
		return "";
	}
	
	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("test");

		return "/WEB-INF/views/test.jsp"; // DispatcherServlet이야 /WEB-INF/views/test.jsp 이거 포워드해라.
	}
	
	
	//삭제
	//@ModelAttribute 일때
   @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
   public String delete(@RequestParam("no") int no) {
      System.out.println("[PhoneController.delete]");


      System.out.println(no);
      
      //Dao 사용
      //PhoneDao phoneDao = new PhoneDao();
      
      //Dao의 personInsert() 이용해서 데이터 저장
      //int count = phoneDao.personDelete(no);
   
      //view --> 리다이렉트
      return "redirect:/list";
   }
   
    //수정폼
	@RequestMapping(value="/updateForm", method = {RequestMethod.GET, RequestMethod.POST} )
	public String updateForm(@RequestParam("no") int personNo, Model model) {
		System.out.println("[PhoneController.updateForm]");
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao();
		
		//Dao의 메소드로 데이터 가져오기
		//PersonVo personVo = phoneDao.getPerson(personNo);
		//System.out.println(personVo);
		
		//model 담기 (택배박스에 담기)
		//model.addAttribute("personVo", personVo);
	
		return "/WEB-INF/views/updateForm.jsp";
	}
	
	//수정
   @RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
   public String update(@RequestParam("no") int personNo, @ModelAttribute PersonVo personVo) {
      System.out.println("[PhoneController.update]");
      
      personVo.setPersonId(personNo);

      System.out.println(personVo);
      
      //Dao 사용
      //PhoneDao phoneDao = new PhoneDao();
      
      //Dao의 personUpdate() 이용해서 데이터 저장
      //int count = phoneDao.personUpdate(personVo);
   
      //view --> 리다이렉트
      return "redirect:/list";
	   }

   
	

}