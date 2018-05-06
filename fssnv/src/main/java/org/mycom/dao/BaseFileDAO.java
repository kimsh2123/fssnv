package org.mycom.dao;

import java.util.List;

import org.mycom.domain.MenuVO;

/*
 * 2016.04.14 
 * Daily Base File Upload 처리
 */

public interface BaseFileDAO {
	
	// Base File select the menu ('D' daily or 'M' monthly), (1=down, 2=upload)
	public List<MenuVO> selectSubject(String menu_type, int flag) throws Exception;
	
	// Get existing data
	public int getExistsData(String menu_id, String make_date) throws Exception;
	
}
