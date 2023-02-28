package com.koreaIT.java.BAM.container;

import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.service.ArticleService;
import com.koreaIT.java.BAM.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}
