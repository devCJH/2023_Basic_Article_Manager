package com.koreaIT.java.BAM.dto;

public class Article {
	
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int viewCnt;
	
	public Article(int id, String regDate, String title, String body){
		this(id, regDate, title, body, 0);
	}
	
	public Article(int id, String regDate, String title, String body, int viewCnt){
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.viewCnt = viewCnt;
	}

	public void addViewCnt() {
		this.viewCnt++;
	}
}
