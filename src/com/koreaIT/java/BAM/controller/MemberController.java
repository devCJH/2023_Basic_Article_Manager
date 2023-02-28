package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = Container.memberDao.members;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		
		switch(methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			showProfile();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}
	
	private void doJoin() {
		int id = Container.memberDao.getLastId();
		String regDate = Util.getDate();
		
		String loginId = null;
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if (loginIdDupChk(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("%s은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		
		String loginPw = null;
		String loginPwChk = null;
		while(true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			loginPwChk = sc.nextLine();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		
		Member member = new Member(id, regDate, loginId, loginPw, name);

		Container.memberDao.add(member);

		System.out.printf("%s회원님 환영합니다\n", loginId);
	}
	
	private void doLogin() {
		
		Member member = null;
		String loginPw = null;
		
		while(true) {
			System.out.printf("로그인 아이디 : ");
			String loginId = sc.nextLine();
			
			if(loginId.trim().length() == 0) {
				System.out.println("로그인 아이디를 입력해주세요");
				continue;
			}
			
			while(true) {
				System.out.printf("로그인 비밀번호 : ");
				loginPw = sc.nextLine();
				
				if(loginPw.trim().length() == 0) {
					System.out.println("로그인 비밀번호를 입력해주세요");
					continue;
				}
				break;
			}

			member = getMemberByLoginId(loginId);
			
			if (member == null) {
				System.out.println("존재하지 않는 아이디 입니다");
				return;
			}
			
			if(member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호를 확인해주세요");
				return;
			}
			break;
		}
		
		loginedMember = member;
		
		System.out.printf("로그인 성공! %s님 환영합니다\n", member.name);
		
	}
	
	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다");
	}
	
	private void showProfile() {
		System.out.println("== 내 정보 ==");
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);
	}
	
	private Member getMemberByLoginId(String loginId) {
		
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		
		return null;
	}

	private boolean loginIdDupChk(String loginId) {
		
		Member member = getMemberByLoginId(loginId);
		
		if (member == null) {
			return true;
		}
		
		return false;
	}
	
	public void makeTestData() {
		System.out.println("회원 테스트 데이터를 생성합니다");
		Container.memberDao.add(new Member(Container.memberDao.getLastId(), Util.getDate(), "test1", "test1", "김철수"));
		Container.memberDao.add(new Member(Container.memberDao.getLastId(), Util.getDate(), "test2", "test2", "김영희"));
		Container.memberDao.add(new Member(Container.memberDao.getLastId(), Util.getDate(), "test3", "test3", "김영수"));
	}
}
