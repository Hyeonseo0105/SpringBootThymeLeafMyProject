package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.MyReplyDAO;
import com.sist.web.entity.Myreply;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyReplyController {

	@Autowired
	private MyReplyDAO dao;
	
	@PostMapping("/reply/insert")
	public String reply_insert(Myreply vo,HttpSession session,RedirectAttributes ra)
	{
		String id=(String)session.getAttribute("id");
	    String name=(String)session.getAttribute("name");
	    vo.setId(id);
	    vo.setName(name);
	    dao.save(vo);//insert
	    ra.addAttribute("gnum", vo.getGnum());
	    
	    return "redirect:/music/detail";
	}
	
	@GetMapping("/reply/delete")
    public String reply_delete(int gnum,int no,RedirectAttributes ra)
    {
	    Myreply vo=dao.findByNo(no);
	    dao.delete(vo);
	    ra.addAttribute("gnum", gnum);
	    return "redirect:/music/detail";
    }
	
	@PostMapping("/reply/update")
	public String reply_update(Myreply vo,RedirectAttributes ra)
	{
		dao.save(vo);
		ra.addAttribute("gnum", vo.getGnum());
	    return "redirect:/music/detail";
	}
}
