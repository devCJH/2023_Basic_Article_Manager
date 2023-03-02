package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao {
	private List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);
			
			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			
			return printArticles;
			
		}
		return articles;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public void articleModify(Article foundArticle, String title, String body) {
		foundArticle.title = title;
		foundArticle.body = body;
	}
}