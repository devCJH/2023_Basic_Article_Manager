package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		return articleDao.getPrintArticles(searchKeyword);
	}

	public int getLastId() {
		return articleDao.getLastId();
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

	public void articleModify(Article foundArticle, String title, String body) {
		articleDao.articleModify(foundArticle, title, body);
	}
	
}
