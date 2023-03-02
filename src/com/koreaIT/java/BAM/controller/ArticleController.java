package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.service.ArticleService;
import com.koreaIT.java.BAM.service.MemberService;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {

	private Scanner sc;
	private String cmd;
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController(Scanner sc) {
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;
		this.sc = sc;
	}

	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch(methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}
	
	private void doWrite() {
		int id = articleService.getLastId();
		String regDate = Util.getDate();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body);

		articleService.add(article);
		
		System.out.printf("%d번 글이 생성되었습니다\n", id);
	}

	private void showList() {
		String searchKeyword = cmd.substring("article list".length()).trim();

		List<Article> printArticles = articleService.getPrintArticles(searchKeyword);
		
		if (printArticles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println("번호	|	제목	|		날짜		|	작성자	|	조회");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			
			String writerName = memberService.getWriterName(article.memberId);
			
			System.out.printf("%d	|	%s	|	%s	|	%s	|	%d\n", article.id, article.title, article.regDate, writerName,
					article.viewCnt);
		}
	}

	private void showDetail() {
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		String writerName = memberService.getWriterName(foundArticle.memberId);
		
		foundArticle.addViewCnt();
		
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.viewCnt);
	}
	
	private void doModify() {
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		if(foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다");
			return;
		}

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		articleService.articleModify(foundArticle, title, body);
		
		System.out.printf("%d번글이 수정되었습니다\n", id);
	}

	private void doDelete() {
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		if(foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다");
			return;
		}

		articleService.remove(foundArticle);

		System.out.printf("%d번 게시글을 삭제했습니다\n", id);
	}
	
	public void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다");
		articleService.add(new Article(articleService.getLastId(), Util.getDate(), 1, "제목1", "내용1", 10));
		articleService.add(new Article(articleService.getLastId(), Util.getDate(), 2, "제목2", "내용2", 20));
		articleService.add(new Article(articleService.getLastId(), Util.getDate(), 2, "제목3", "내용3", 30));
	}
}
