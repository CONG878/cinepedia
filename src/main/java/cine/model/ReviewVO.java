package cine.model;

import java.sql.Timestamp;

public class ReviewVO {
	private int rnum;// 리뷰 번호
	private int fnum;// 영화 번호
	private String loginId;
	private int rating;// 평점
	private Timestamp rvdate;// 리뷰 날짜
	private String comment;// 리뷰 내용

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getFnum() {
		return fnum;
	}

	public void setFnum(int fnum) {
		this.fnum = fnum;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Timestamp getRvdate() {
		return rvdate;
	}

	public void setRvdate(Timestamp rvdate) {
		this.rvdate = rvdate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
