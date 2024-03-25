package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Musicnews;

@Repository
public interface NewsDAO extends JpaRepository<Musicnews, Integer>{

	@Query(value = "SELECT * FROM musicnews "
			+"WHERE cateno=2 "
			+"ORDER BY no DESC "
			+"LIMIT :start,9",nativeQuery = true)
	public List<Musicnews> newsListData(@Param("start") int start);
	
	@Query(value= "SELECT COUNT(*) FROM musicnews "
			+"WHERE cateno=2 ",nativeQuery = true)
	public int newsCount();
	
	public Musicnews findByNo(int no);
	
	//홈
	@Query(value = "SELECT * FROM musicnews "
			+"WHERE no=629",nativeQuery = true)
	public List<Musicnews> newsHomeData();
}
