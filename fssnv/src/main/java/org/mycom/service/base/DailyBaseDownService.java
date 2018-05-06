package org.mycom.service.base;

import java.util.List;

/*
 * 2016.04.29 Base file Download Process 
 */

public interface DailyBaseDownService {
	
	// file download
	public String fileDown(String make_date, String up_id, List<String> menu_id) throws Exception;
	
	// Zip File Search
	public String searchZipFile(String make_date) throws Exception;
	

}
