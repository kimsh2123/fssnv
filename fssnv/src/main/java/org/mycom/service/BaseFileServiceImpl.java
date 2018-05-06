package org.mycom.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.mycom.dao.BaseFileDAO;
import org.mycom.domain.MenuVO;
import org.mycom.util.FssENV;
import org.springframework.stereotype.Service;

@Service
public class BaseFileServiceImpl implements BaseFileService {
	
	@Inject
	private BaseFileDAO dao;

	@Override
	public List<MenuVO> selectSubject(String menu_type, int flag) throws Exception {
		return dao.selectSubject(menu_type, flag);
	}

	@Override
	public HashMap<String, Integer> getExistsData(List<MenuVO> list, String make_date) throws Exception {
		
		HashMap<String, Integer> tmp_hash = new HashMap<String, Integer>();
		String menu_id = "";
		for(MenuVO vo : list){
			menu_id = vo.getMenu_id();
			tmp_hash.put(menu_id, dao.getExistsData(menu_id, make_date));
		}
		
		return tmp_hash;
	}

	@Override
	public HashMap<String, String> readDailyFileCount(String make_date) throws Exception {
		
		int rtn = 0;
		HashMap<String, String> tmp_hash = new HashMap<String, String>();
		String filename = FssENV.BaseFileDir + "/FD" + FssENV.Member_no + make_date;
		
		File fi = new File(filename);
		if(fi.isFile()){
			FileInputStream fis = new FileInputStream(filename);
			Reader reader = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(reader);
			
			while( br.readLine() != null ){
				rtn++;
			}
			br.close();
			fis.close();
			
			tmp_hash.put("filename", "FD" + FssENV.Member_no + make_date);
			tmp_hash.put("basefilecnt", Integer.toString(rtn));
		} else {
			tmp_hash.put("filename", "this file does not exist");
			tmp_hash.put("basefilecnt", "0");
		}
		
		return tmp_hash;
	}

}
