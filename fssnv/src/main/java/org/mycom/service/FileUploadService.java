package org.mycom.service;

/*
 * 2016.03.22 file read & insert 처리
 */

public interface FileUploadService {
	
	// file read
	public int readFile(String filepath, String up_id) throws Exception;
	
}
