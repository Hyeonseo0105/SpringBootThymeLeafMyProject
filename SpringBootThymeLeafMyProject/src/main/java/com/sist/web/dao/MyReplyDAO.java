package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Myreply;

public interface MyReplyDAO extends JpaRepository<Myreply, Integer>{

	@Query(value = "SELECT * FROM myreply WHERE gnum=:gnum ORDER BY no ASC",nativeQuery = true)
	public List<Myreply> replyListData(@Param("gnum") int gnum);
	
	public Myreply findByNo(int no);
}
