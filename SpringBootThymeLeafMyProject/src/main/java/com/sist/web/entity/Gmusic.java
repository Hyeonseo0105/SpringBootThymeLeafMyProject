package com.sist.web.entity;
/*
 * GNUM int 
GRANK int 
GTITLE text 
GENRE text 
GTYPE int 
ARTIST text 
SONG text 
RANK_CHANGE text 
RANK_VALUE text 
IMAGE text 
MV text
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Gmusic {

	@Id
	@Column(insertable = false,updatable = false)
	private int gnum;
	private int grank,gtype;
	private String gtitle,artist,song,rank_change,rank_value,image;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gnum",referencedColumnName = "gnum")
	private Gmusicdetail gmusicdetail;
}
