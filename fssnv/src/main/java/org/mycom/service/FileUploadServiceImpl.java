package org.mycom.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.inject.Inject;

import org.mycom.controller.FileUploadController;
import org.mycom.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Inject
	private FileFmtService ffs;
	
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
				
				rtn = rtn + ffs.splitAg113(readdata, up_id);
				logger.info("splitAg113>>>"+rtn);
				
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
