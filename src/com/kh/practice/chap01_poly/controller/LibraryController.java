package com.kh.practice.chap01_poly.controller;

import com.kh.practice.chap01_poly.model.vo.AniBook;
import com.kh.practice.chap01_poly.model.vo.Book;
import com.kh.practice.chap01_poly.model.vo.CookBook;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryController {
	
	private Member mem = null;
	private Book[] bList = new Book[5];
	
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("한번 더 해요", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	
	public void insertMember(Member mem) {
		
		this.mem = mem;
		
	}
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	public Book[] searchBook(String keyword) {
		Book[] bArr = new Book[5];
		int count = 0; //검색결과의 도서목록에 담기에 count를 쓰라는데 이유를 모르겠음;
		
		for(int i=0; i<bList.length; i++) {
			if(bList[i].getTitle().contains(keyword)){
				bArr[i] = bList[i];
			}
		}
		return bArr;
	}
	public int rentBook(int index) {
		int result = 0;//result 0으로 초기화
		if (index == 1 || index == 2) {//index가 1ㄹ
			if (mem.getAge() < ((AniBook) bList[index]).getAccessAge()) {
				// 회원의 나이와 해당 만화책의 제한나이를 비교하여 회원 나이가 더 적은 경우 result 를 1 로 초기화
				result = 1;
			}
		}
		if (index == 0 || index == 3 || index == 4) {// 인덱스가 0,3,4인 경우 CookBook
			mem.setCouponCount(mem.getCouponCount() + 1);
			//		해당 요리책의 쿠폰 유무가 유일 경우 회원의 couponCount를 1 증가 시킨 후 result 2 로 초기화
			result = 2;
		}
		return result;
	}
}
