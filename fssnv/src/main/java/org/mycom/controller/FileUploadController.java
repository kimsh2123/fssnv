package org.mycom.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.mycom.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * 2016.03.21 file upload 관련 controller by sung
 */

@Controller
@RequestMapping("/fileupdw/*")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Inject
	private FileUploadService f_service;
	
	// 각 daily menu 별 file upload 화면 Call 
	@RequestMapping(value = "/fileupload", method = RequestMethod.GET)
	public void uploadGET(Model model
			,@RequestParam(value="menu_id", defaultValue="") String menu_id) throws Exception{
		
		logger.info("FileUploadController uploadGET ...>"+menu_id);
		model.addAttribute("menu_id", menu_id);
	}
	
	// 각 daily menu 별 file upload 처리
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String uploadPOST(MultipartFile file, RedirectAttributes rttr
			,@RequestParam(value="menu_id", defaultValue="") String menu_id
			,@RequestParam(value="up_id", defaultValue="") String up_id) throws Exception{
		
		logger.info("uploadPOST["+menu_id+"]File Name : "+file.getOriginalFilename()
					+", Size: "+file.getSize()+", ContentType: "+file.getContentType()
					+", menu_id: "+menu_id+", up_id: "+up_id);
		
		// File Upload
		File tgfile = new File(uploadPath, file.getOriginalFilename());
		FileCopyUtils.copy(file.getBytes(), tgfile);
		
		// File Read/Write
		int rtn = 0;
		if(tgfile.exists()){
			
			rtn = f_service.readFile(uploadPath+"/"+file.getOriginalFilename(), up_id);
			
			logger.info("file exists..."+uploadPath+"/"+file.getOriginalFilename()+", RESULT CNT:"+rtn);
			
		}
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		rttr.addFlashAttribute("count", rtn);
		
		return "redirect:/daily/"+menu_id;
	}
	
}
