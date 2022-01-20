package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guest")
public class GuestbookController {

	// 필드

	// 생성자

	// 메소드 gs

	// 메소드 일반
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("GuestbookController>addList()");

		// 다오에서 리스트를 가져온다
		GuestbookDao guestbookDao = new GuestbookDao();
		List<GuestbookVo> guestbookList = guestbookDao.getList();
		System.out.println(guestbookList.toString());

		// 컨트롤러-->DS데이터를 보낸다 (model)
		model.addAttribute("gList", guestbookList);

		// jsp 정보를 리턴한다(view)
		return "addList";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController>add()");
		System.out.println(guestbookVo);

		// 저장
		GuestbookDao guestbookDao = new GuestbookDao();
		guestbookDao.insert(guestbookVo);

		// 리다이렉트
		return "redirect:/guest";
	}

	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no) {
		System.out.println("GuestbookController>deleteForm()");
		
		return "deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam(value="no") int no,
						 @RequestParam(value = "password") String password) {
		System.out.println("GuestbookController>delete()");
		
		// dao 메모리에 올리기
		GuestbookDao guestbookDao = new GuestbookDao();

		GuestbookVo guestbookVo = new GuestbookVo(no, password);
		
		// dao.delete(vo);
		guestbookDao.delete(guestbookVo);

		// 리다이렉트
		return "redirect:/guest";
	}

}
