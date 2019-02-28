/**
 * 
 */
package com.ola.qh.entity;

import java.util.Date;

/**
 * 
* @ClassName: 
* @Description:  
* @author guozihan
* @date   
*
 */
public class UserShare {

	private String id;
	
	private String userId;
	
	private String sectionId;
	
	private Date addtime;

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

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
}
