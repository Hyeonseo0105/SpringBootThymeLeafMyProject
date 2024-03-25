package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sist.web.entity.Gmusic;

import groovyjarjarpicocli.CommandLine.Parameters;

@Repository
public interface MusicDAO extends JpaRepository<Gmusic, Integer> {

	// 홈
	@Query(value="SELECT * "
			+"FROM gmusic ORDER BY gnum ASC "
			+"LIMIT 0,8",nativeQuery = true)
	public List<Gmusic> musicMainData();
	
	public Gmusic findByGnum(int gnum);
	
	// 차트200
	@Query(value = "SELECT * FROM gmusic "
			+"WHERE gtype=1 "
			+"ORDER BY grank ASC "
			+"LIMIT :start,20",nativeQuery = true)
	public List<Gmusic> musicListData(@Param("start") int start);

	@Query(value = "SELECT COUNT(*) FROM gmusic "
			+"WHERE gtype=1 ",nativeQuery = true)
	public int musicCount();
	
	@Query(value = "SELECT * "
			+"FROM gmusic WHERE artist LIKE CONCAT('%',:artist,'%') "
			+"ORDER BY grank ASC "
			+"LIMIT :start,20",nativeQuery = true)
	public List<Gmusic> artistFindData(@Param("start") Integer start,@Param("artist") String artist);
	
	@Query(value = "SELECT * "
			+"FROM gmusic WHERE song LIKE CONCAT('%',:song,'%') "
			+"ORDER BY grank ASC "
			+"LIMIT :start,20",nativeQuery = true)
	public List<Gmusic> songFindData(@Param("start") Integer start,@Param("song") String song);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) "
			+"FROM gmusic WHERE artist LIKE CONCAT('%',:artist,'%')",nativeQuery = true)
	public int artistFindTotalPage(@Param("artist") String artist);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) "
			+"FROM gmusic WHERE artist LIKE CONCAT('%',:song,'%')",nativeQuery = true)
	public int songFindTotalPage(@Param("song") String song);
}
