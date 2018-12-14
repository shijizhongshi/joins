package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class ShopDrugCategory {

	private String id;
	
	@NotEmpty(message="药品分类不能为空")
	private String categoryName;
	
	private String categoryId;///子类别的id
	
	private String subName;////子类别的名称
	
	private String imgUrl;////图片地址
	
	private Date addtime;
	
	private Date updatetime;
	
	private List<ShopDrugCategory> list =new ArrayList<ShopDrugCategory>();
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public List<ShopDrugCategory> getList() {
		return list;
	}

	public void setList(List<ShopDrugCategory> list) {
		this.list = list;
	}
	
}
