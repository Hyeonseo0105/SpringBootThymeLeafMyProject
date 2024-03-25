package com.sist.web.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.BoardDAO;
import com.sist.web.dao.MusicDAO;
import com.sist.web.dao.NewsDAO;
import com.sist.web.entity.Board;
import com.sist.web.entity.Gmusic;
import com.sist.web.entity.Musicnews;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@Autowired
	private MusicDAO dao;
	
	@Autowired
	private NewsDAO nDao;
	
	@Autowired
	private BoardDAO bDao;
	
	@GetMapping("/")
	public String main_page(Model model,HttpServletRequest request)
	{
		// 쿠키
		Cookie[] cookies=request.getCookies();
		List<Gmusic> cList=new ArrayList<Gmusic>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("music"))
				{
					if(k>8)
						break;
					String gnum=cookies[i].getValue();
					Gmusic g=dao.findByGnum(Integer.parseInt(gnum));
					cList.add(g);
					k++;
				}
			}
		}
		
		model.addAttribute("cList", cList);
		
		List<Gmusic> list=dao.musicMainData();
		List<Musicnews> nList=nDao.newsHomeData();
		List<Board> bList=bDao.boardMainData();
		
		model.addAttribute("list", list);
		model.addAttribute("nList", nList);
		model.addAttribute("bList", bList);
		
		model.addAttribute("main_html","main/home");
		return "main";
	}
}
