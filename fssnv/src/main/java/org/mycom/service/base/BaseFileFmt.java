package org.mycom.service.base;

/*
 * 2016.04.22 Base file insert & download 처리 interface
 */

public interface BaseFileFmt {
	
	// base file upload process
	public int process(String str, String up_id) throws Exception;
	
	// base file download process 4.29
	public int write(String make_date, String up_id) throws Exception;

}
