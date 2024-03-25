package com.sist.web.entity;
/*
 * MNUM int 
GNUM int 
LINK text 
LYRIC text 
REGDATE text 
TAG text
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gmusicdetail {

	@Id
	private int gnum;
	private int mnum;
	private String lyric,regdate,tag;

}
