package org.mycom.service.base;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.mycom.controller.DailyBaseDownController;
import org.mycom.util.CompZip;
import org.mycom.util.FssENV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DailyBaseDownServiceImpl implements DailyBaseDownService {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyBaseDownController.class);
	
	@Inject
	@Named("baseAg113FmtImpl")
	private BaseFileFmt ag113;

	@Override
	public String fileDown(String make_date, String up_id, List<String> menu_id) throws Exception {
		
		String rtn = "";
		
		for(String value : menu_id){
			
			// 자료별 create file
			if(value.equals("ag113")){
				ag113.write(make_date, up_id);
			} else {
				continue;
			}
			
		}
		
		// Create Zip
		rtn = CompZip.createZipFile(make_date, FssENV.BaseFileDown
				, "Zip_"+make_date+".zip", FssENV.BaseFileDir);
		
		logger.info("DailyBaseDownServiceImpl rtn >>>"+rtn);
		
		return rtn;
	}

	@Override
	public String searchZipFile(String make_date) throws Exception {
		String rtn = "N";
		String path = FssENV.BaseFileDir + "/Zip_"+make_date+".zip";
		File tg_zip = new File(path);
		
		// 해당일 zip 파일 존재 여부 check
		if(tg_zip.isFile()){
			rtn = "Zip_"+make_date+".zip";
		}
		
		return rtn;
	}

}
