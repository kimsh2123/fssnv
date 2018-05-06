package org.mycom.domain;

/*
 * Login 데이터 전달 Data transfer object
 */
public class LoginDTO {

	private String user_id;
	private String user_passwd;
	private boolean useCookie;
	
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
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [user_id=" + user_id + ", user_passwd=" + user_passwd + ", useCookie=" + useCookie + "]";
	}
	
}
