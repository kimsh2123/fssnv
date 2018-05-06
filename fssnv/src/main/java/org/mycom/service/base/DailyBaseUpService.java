package org.mycom.service.base;

/*
 * 2016.04.22 daily base file upload & insert
 */

public interface DailyBaseUpService {
	
	// read file & process
	public int readFile(String filepath, String up_id) throws Exception;

}
