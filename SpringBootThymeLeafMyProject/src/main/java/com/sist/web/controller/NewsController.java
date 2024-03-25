package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.web.dao.NewsDAO;
import com.sist.web.entity.Musicnews;

@Controller
public class NewsController {

	@Autowired
	private NewsDAO dao;
	
	@GetMapping("/news/list")
	public String news_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=9;
		int start=(rowSize*curpage)-rowSize;
		List<Musicnews> list=dao.newsListData(start);
		
		int count=dao.newsCount();
		int totalpage=(int)(Math.ceil(count/9));
		
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
		
		model.addAttribute("main_html", "news/list");
		return "main";
	}
	
	@GetMapping("/news/detail")
	public String news_detail(int no, Model model)
	{
		Musicnews vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "news/detail");
		
		return "main";
	}
}
