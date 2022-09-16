package dto;

import java.util.Date;

public class Review {
	private String youtubeId;
	private int reviewId;
	private String title;
	private String writer;
	private String content;
	private int viewCnt;
	private Date regDate;

	public Review() {
		super();
	}

	public Review(int reviewId, String title, String content, int viewCnt, Date regDate, String writer, String youtubeId) {
		super();
		this.youtubeId = youtubeId;
		this.reviewId = reviewId;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", viewCnt=" + viewCnt + ", regDate=" + regDate + "]";
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

}
