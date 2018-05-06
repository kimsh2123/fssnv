package org.mycom.domain;

/*
 * 공통 코드 가져오기 code_tbl
 */

public class CodeVO {
	
	private String subject;
	private int types;
	private String param;
	private String t_name;
	private String p_name;
	private String remark;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "CodeVO [subject=" + subject + ", types=" + types + ", param=" + param + ", t_name=" + t_name
				+ ", p_name=" + p_name + ", remark=" + remark + "]";
	} 
	
}
