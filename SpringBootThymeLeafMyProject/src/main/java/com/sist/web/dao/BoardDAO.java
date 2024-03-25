package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
import com.sist.web.entity.*;

public interface BoardDAO extends JpaRepository<Board, Integer>{
	
	// 상세보기
	public Board findByNo(int no);
	
	@Query(value = "SELECT * "
			+"FROM board ORDER BY no DESC "
			+"LIMIT :start,10",nativeQuery = true)
	public List<Board> boardListData(@Param("start") int start);

	@Query(value = "SELECT COUNT(*) FROM board",nativeQuery = true)
	public int boardCount();
	
	// 홈
	@Query(value = "SELECT * "
			+"FROM board ORDER BY hit DESC "
			+"LIMIT 0,3",nativeQuery = true)
	public List<Board> boardMainData();
}
