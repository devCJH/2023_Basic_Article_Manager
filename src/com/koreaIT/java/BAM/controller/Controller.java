package com.koreaIT.java.BAM.controller;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
	
	public static Member loginedMember;
	
	public boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void doAction(String cmd, String methodName);
	
	public abstract void makeTestData();
}
