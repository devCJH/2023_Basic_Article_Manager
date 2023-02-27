package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao {
	public List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}
}
