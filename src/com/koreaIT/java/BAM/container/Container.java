package com.koreaIT.java.BAM.container;

import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
