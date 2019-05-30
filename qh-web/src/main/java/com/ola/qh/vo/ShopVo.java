package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.ShopImg;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.UserComment;

public class ShopVo{
	
	
	private String id;
	
	private String userId;
	private int shopType;////1:服务店铺    2:商城店铺
	
	private String shopName;////店铺名称
	
	private String address;////
	
	private String doorHeadUrl;//////门口图片
		
	private String shopLogo;//////商城参数的logo
	
	private String licenceUrl;/////商城的许可证
	
	private String remarked;////备注
	
	private String businessHours;////营业时间
	
	private List<ShopImg> environmentImgList=new ArrayList<ShopImg>();
	
	private String idcard;////负责人的身份证号
	
	private String realname;////负责人的姓名

	private String serveDomain;////服务领域(中医推拿   小儿推拿等)
	
	private double avgGrades;
	
	private int commentCount;///评论的总个数
	
	private List<ShopServe>  reserveList=new ArrayList<ShopServe>();///预定的集合
	
	private List<ShopServe> groupList=new ArrayList<ShopServe>();////团购的集合
	
	public List<UserComment> commentList=new ArrayList<UserComment>();////评论的集合

	public double getAvgGrades() {
		return avgGrades;
	}

	public void setAvgGrades(double avgGrades) {
		this.avgGrades = avgGrades;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public List<ShopServe> getReserveList() {
		return reserveList;
	}

	public void setReserveList(List<ShopServe> reserveList) {
		this.reserveList = reserveList;
	}

	public List<ShopServe> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<ShopServe> groupList) {
		this.groupList = groupList;
	}

	public List<UserComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<UserComment> commentList) {
		this.commentList = commentList;
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

	public int getShopType() {
		return shopType;
	}

	public void setShopType(int shopType) {
		this.shopType = shopType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDoorHeadUrl() {
		return doorHeadUrl;
	}

	public void setDoorHeadUrl(String doorHeadUrl) {
		this.doorHeadUrl = doorHeadUrl;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getLicenceUrl() {
		return licenceUrl;
	}

	public void setLicenceUrl(String licenceUrl) {
		this.licenceUrl = licenceUrl;
	}

	public String getRemarked() {
		return remarked;
	}

	public void setRemarked(String remarked) {
		this.remarked = remarked;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public List<ShopImg> getEnvironmentImgList() {
		return environmentImgList;
	}

	public void setEnvironmentImgList(List<ShopImg> environmentImgList) {
		this.environmentImgList = environmentImgList;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getServeDomain() {
		return serveDomain;
	}

	public void setServeDomain(String serveDomain) {
		this.serveDomain = serveDomain;
	}
	
	
	
}
