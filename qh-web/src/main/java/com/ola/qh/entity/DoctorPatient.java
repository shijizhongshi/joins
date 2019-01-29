package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class DoctorPatient {

	private String id;
	
	@NotEmpty
	private String userId;
	
	private String title;
	@NotEmpty
	private String describes;
	
	public List<DoctorPatientImg> imglist=new ArrayList<DoctorPatientImg>();
	
	private Date addtime;
	
	private Date updatetime;
	
	private String publisher;////发布人的信息
	
	private String publisherHeadImgUrl;////发布人的信息
	
	private String publisherPosition;////发布者的职位
	
	public int readStatus;////读的状态1:患者已读    2:医生已读   3:都读了
	
	public int replyCount;/////回复的个数
		
	@NotEmpty
	private String category;/////经验分享,问题解答
	
	private int likes;////点赞个数
	
	private String showtime;
	
	private List<Reply> replylist=new ArrayList<Reply>();
	
	
	private int islikes;
	
	
	
	public int getIslikes() {
		return islikes;
	}

	public void setIslikes(int islikes) {
		this.islikes = islikes;
	}

	public List<Reply> getReplylist() {
		return replylist;
	}

	public void setReplylist(List<Reply> replylist) {
		this.replylist = replylist;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getPublisherPosition() {
		return publisherPosition;
	}

	public void setPublisherPosition(String publisherPosition) {
		this.publisherPosition = publisherPosition;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}


	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public List<DoctorPatientImg> getImglist() {
		return imglist;
	}

	public void setImglist(List<DoctorPatientImg> imglist) {
		this.imglist = imglist;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public String getPublisherHeadImgUrl() {
		return publisherHeadImgUrl;
	}

	public void setPublisherHeadImgUrl(String publisherHeadImgUrl) {
		this.publisherHeadImgUrl = publisherHeadImgUrl;
	}


	
}
