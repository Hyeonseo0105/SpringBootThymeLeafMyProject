package com.sist.web.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sist.web.entity.*;
import com.sist.web.dao.*;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/list")
	public String boardList(String page,Model model)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-rowSize;
		List<Board> list=dao.boardListData(start);
		
		int count=dao.boardCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
				
		model.addAttribute("main_html","board/list");
		return "main";
	}
	
	@GetMapping("/board/insert")
	public String boardInsert(Model model)
	{
		model.addAttribute("main_html", "board/insert");
		return "main";
	}
	
	@PostMapping("/board/insert_ok")
	public String boardInsertOk(Board vo)
	{
		dao.save(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/detail")
	public String boradDetail(int no, Model model)
	{
		Board vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_html", "board/detail");
		return "main";
	}
}
