package org.mycom.service.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.inject.Inject;
import javax.inject.Named;

import org.mycom.controller.DailyBaseUpController;
import org.mycom.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 2016.04.22 Daily Base File Upload -> MAIN PROCESS
 */

@Service
public class DailyBaseUpServiceImpl implements DailyBaseUpService {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyBaseUpController.class);
	
	@Inject
	@Named("baseAg113FmtImpl")
	private BaseFileFmt ag113;
	
	
	// Transaction ADD [2016.04.25]
	@Transactional
	@Override
	public int readFile(String filepath, String up_id) throws Exception {
		
		FileInputStream fis = new FileInputStream(filepath);
		Reader reader = new InputStreamReader(fis, "utf-8");
		BufferedReader br = new BufferedReader(reader);
		
		String rdata = null;
		String subject_key = null;
		int rtn = 0;
		while( (rdata = br.readLine()) != null ){
			
			// for 한글 입력 byte 읽기 위해
			String readdata = Utils.e2k(rdata);
			
			// 항목 값을 key로 file format 결정
			subject_key = readdata.substring(0, 5);
			
			logger.info("["+subject_key+"]rdata : "+readdata.length() + ">>>" + readdata);
			
			if(subject_key.equals("AG113")){
				
				rtn = rtn + ag113.process(readdata, up_id);
				logger.info("AG113>>>"+rtn);
				
			} else if(subject_key.equals("AG115")){

				// 차례대로 구현하기
				logger.info("AG115>>>"+rtn);
				
			} else {
				logger.info("No format rdata ["+subject_key+"]");
				continue;
			}
			
		}
		
		br.close();
		fis.close();
		
		return rtn;
	}

}
