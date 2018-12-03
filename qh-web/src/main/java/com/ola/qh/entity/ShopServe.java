package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ShopServe {

	private String id;
	
	private String userId;////用户的id
	@NotEmpty(message="店铺的标识不能为空")
	private String shopId;/////店铺的id
	@NotEmpty(message="服务名称不能为空")
	private String serveName;///服务名称
	@NotNull
	private BigDecimal price;///原价
	@NotNull
	private BigDecimal discountPrice;////折扣价格
	@NotNull
	private String imgUrl;////主图片
	
	private String content;////详细介绍
	
	private String serveTime;////服务时间
	
	private String refundRule;///退款规则
	
	private int serveStatus;///状态:0:没有审批   1:审批过了
	
	private String explains;///其他说明
	
	private Date addtime;
	@Valid
	@NotNull
	@Size(min=1)
	public List<ShopServeImg> imglist=new ArrayList<ShopServeImg>();

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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getServeName() {
		return serveName;
	}

	public void setServeName(String serveName) {
		this.serveName = serveName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getServeTime() {
		return serveTime;
	}

	public void setServeTime(String serveTime) {
		this.serveTime = serveTime;
	}

	public String getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}

	public int getServeStatus() {
		return serveStatus;
	}

	public void setServeStatus(int serveStatus) {
		this.serveStatus = serveStatus;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public List<ShopServeImg> getImglist() {
		return imglist;
	}

	public void setImglist(List<ShopServeImg> imglist) {
		this.imglist = imglist;
	}
	
	
}
