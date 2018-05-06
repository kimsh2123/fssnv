package org.mycom.domain;

import java.util.Date;

public class UserVO {
	
	private String user_id;		
	private String user_passwd;	
	private String user_name;
	private String user_office;	
	private String user_lock;
	private String user_check;	
	private Date up_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_passwd() {
		return user_passwd;
	}
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_office() {
		return user_office;
	}
	public void setUser_office(String user_office) {
		this.user_office = user_office;
	}
	public String getUser_lock() {
		return user_lock;
	}
	public void setUser_lock(String user_lock) {
		this.user_lock = user_lock;
	}
	public String getUser_check() {
		return user_check;
	}
	public void setUser_check(String user_check) {
		this.user_check = user_check;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
	
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", user_passwd=" + user_passwd + ", user_name=" + user_name
				+ ", user_office=" + user_office + ", user_lock=" + user_lock + ", user_check=" + user_check
				+ ", up_date=" + up_date + "]";
	}

}
