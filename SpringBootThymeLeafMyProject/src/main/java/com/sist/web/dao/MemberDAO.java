package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Member;

public interface MemberDAO extends JpaRepository<Member, String>{

	@Query(value = "SELECT COUNT(*) FROM thmember "
			+"WHERE id=:id",nativeQuery = true)
	public int memberIdCount(@Param("id") String id);
	
	@Query(value = "SELECT * "
			+"FROM thmember WHERE id=:id",nativeQuery = true)
	public Member memberInfoData(@Param("id") String id);
}
