package com.sist.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sist.web.dao.MusicDAO;
import com.sist.web.dao.MyReplyDAO;
import com.sist.web.entity.Gmusic;
import com.sist.web.entity.Myreply;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ChartController {

	@Autowired
	private MusicDAO dao;
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private MyReplyDAO rDao;
	
	@GetMapping("/music/chart")
	public String music_chart(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Gmusic> list=dao.musicListData(start);
		
		int count=dao.musicCount();
		int totalpage=(int)(Math.ceil(count/20));
		
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
		
		model.addAttribute("main_html", "music/chart");
		return "main";
	}
	
	@GetMapping("/music/before_detail")
	public String music_before_detail(int gnum,RedirectAttributes ra,HttpServletResponse response)
	{
		// 쿠키
		Cookie cookie=new Cookie("music"+gnum, String.valueOf(gnum));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("gnum", gnum);
		return "redirect:../music/detail";
	}
	
	@GetMapping("/music/detail")
	public String music_detail(int gnum,Model model)
	{
		// 댓글
		List<Myreply> list=rDao.replyListData(gnum);
		model.addAttribute("list", list);
		
		String sql="SELECT m FROM Gmusic m JOIN m.gmusicdetail gmd WHERE m.gnum=:gnum "
				  +"AND m.gnum=:gnum";
		Gmusic m=em.createQuery(sql, Gmusic.class).setParameter("gnum", gnum).getSingleResult();
		model.addAttribute("vo", m);
		
		model.addAttribute("main_html", "music/detail");
		return "main";
	}
	
	@RequestMapping("/music/find")
	public String music_find(String page,String artist,Model model)
	{
		if(artist==null)
			artist="아이유";
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		
		List<Gmusic> aList=dao.artistFindData(start,artist);
		
		int totalpage=dao.artistFindTotalPage(artist);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("aList", aList);
		model.addAttribute("artist", artist);
		
		model.addAttribute("main_html","music/find");
		return "main";
	}
	
	@RequestMapping("/music/songfind")
	public String music_song_find(String page,String song,Model model)
	{
		if(song==null)
			song="음악";
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		
		List<Gmusic> sList=dao.songFindData(start,song);
		
		int totalpage=dao.songFindTotalPage(song);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("sList", sList);
		model.addAttribute("song", song);
		
		model.addAttribute("main_html","music/songfind");
		return "main";
	}
}
