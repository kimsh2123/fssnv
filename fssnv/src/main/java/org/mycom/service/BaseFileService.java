package org.mycom.service;

import java.util.HashMap;
import java.util.List;

import org.mycom.domain.MenuVO;

/*
 * 2016.04.14 
 * Daily Base File Upload 처리
 */

public interface BaseFileService {
	
	// Base File select the menu ('D' daily or 'M' monthly), (1=down, 2=upload)
	public List<MenuVO> selectSubject(String menu_type, int flag) throws Exception;
	
	// Get existing data
	public HashMap<String, Integer> getExistsData(List<MenuVO> list, String make_date) throws Exception;
	
	// read daily basefile count
	public HashMap<String, String> readDailyFileCount(String make_date) throws Exception;
	
}
