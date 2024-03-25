package com.sist.web.entity;

import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Musicnews {

	@Id
	private int no;
	private int cateno;
	private String title;
	private String poster,poster2,regdate;
}
