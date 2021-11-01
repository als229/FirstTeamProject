package com.kh.practice.chap01_poly.view;

import java.util.Scanner;

import com.kh.practice.chap01_poly.controller.LibraryController;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryMenu {

	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {

		Scanner sc = new Scanner(System.in);

		// 이름, 나이 성별을 키보드로 입력받은 후 Member 객체 생성
		System.out.println("이름 : ");
		String name = sc.nextLine();
		System.out.println("나이 : ");
		int age = sc.nextInt();
		System.out.println("성별 : ");
		char gender = sc.next().charAt(0);

		//		 LibraryController의 insertMember() 메소드에 전달

		lc.insertMember(new Member(name, age ,gender));
		while(true) {
			System.out.println("\n==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 전체 조회");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 대여하기");
			System.out.println("9. 프로그램 종료하기");
			System.out.println("메뉴 번호: ");
			int inputNum = sc.nextInt();

			// 메뉴 번호 입력 후 해당 번호 메소드 호출
			switch(inputNum) {
			case 1:
				System.out.println(lc.myInfo());
				break;
			case 2:
				selectAll();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				rentBook();
				break;
			case 9:
				System.out.print("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("다시 입력해주세요.");
				continue;
			}
		} // while 끝
	}
	public void selectAll() {

		for(int i = 0; i < lc.selectAll().length; i++) {

			System.out.println(i + "번도서 : " + lc.selectAll()[i]);

		}
	}
	public void searchBook() {
		System.out.println("검색할 제목 키워드 : ");
		String keyword = sc.nextLine();

		for(int i = 0; i < lc.searchBook(keyword).length; i++) {
			if(lc.searchBook(keyword)[i] != null) {
			System.out.println(lc.searchBook(keyword)[i]);
			}
		}
		
		return;
	}
	public void rentBook() {
		selectAll();
		System.out.print("대여할 도서 번호 선택 : ");
		int index = sc.nextInt();//인덱스 입력 받음
		int result = lc.rentBook(index);//index에 해당하는 rentBook를 반환받은 값을 result에 대입
		switch (result) {
		case 0:
			System.out.println("성공적으로 대여되었습니다.");
			break;
		case 1:
			System.out.println("나이 제한으로 대여 불가능입니다.");
			break;
		case 2:
			System.out.println("성공적으로 대여되었습니다 . 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요");
			break;
		default:
		}
	}


}
