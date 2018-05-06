package org.mycom.domain;

public class MenuVO {
	
	private int menu_code_1;
	private int menu_code_2;
	private int menu_code_3;
	private String menu_name;
	private String menu_uri;
	private String menu_type;
	private int support;
	private String menu_id;
	
	public int getMenu_code_1() {
		return menu_code_1;
	}
	public void setMenu_code_1(int menu_code_1) {
		this.menu_code_1 = menu_code_1;
	}
	public int getMenu_code_2() {
		return menu_code_2;
	}
	public void setMenu_code_2(int menu_code_2) {
		this.menu_code_2 = menu_code_2;
	}
	public int getMenu_code_3() {
		return menu_code_3;
	}
	public void setMenu_code_3(int menu_code_3) {
		this.menu_code_3 = menu_code_3;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_uri() {
		return menu_uri;
	}
	public void setMenu_uri(String menu_uri) {
		this.menu_uri = menu_uri;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	@Override
	public String toString() {
		return "MenuVO [menu_code_1=" + menu_code_1 + ", menu_code_2=" + menu_code_2 + ", menu_code_3=" + menu_code_3
				+ ", menu_name=" + menu_name + ", menu_uri=" + menu_uri + ", menu_type=" + menu_type + ", support="
				+ support + ", menu_id=" + menu_id + "]";
	}
	
}
