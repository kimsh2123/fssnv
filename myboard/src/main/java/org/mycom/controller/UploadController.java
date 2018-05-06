package org.mycom.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.mycom.util.MediaUtils;
import org.mycom.util.UploadFileUtils;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	// file 저장 경로 가져오기 servlet-context.xml
	@Resource(name = "uploadPath")
	private String uploadPath;

	// 1.3 테스트 GET 방식 입력 처리, 최초 페이지 보여주기 위함
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
	}

	// 1.3 테스트 POST 방식 입력 처리
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());

		String savedName = uploadFile(file.getOriginalFilename(),
				file.getBytes());

		model.addAttribute("savedName", savedName);

		return "uploadResult";
	}

	// 원본 파일 이름과 데이터를 byte[]로 변환한 정보를 파라미터로 처리해 실제로 파일을 업로드
	private String uploadFile(String originalName, byte[] fileData)
			throws Exception {

		// UUID 는 중복되지 않는 고유값을 사용하기 위해 (거의 고유)
		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalName;

		File target = new File(uploadPath, savedName);

		// FileCopyUtils은 파일 데이터를 파일로 처리하거나, 복사 등 작업에 사용
		FileCopyUtils.copy(fileData, target);

		return savedName;

	}

	// Ajax로 업로드 처리, 최초 페이지 보여주기 위함
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
	}

	// Ajax로 업로드 처리, produces는 한국어 처리를 위해
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file)
			throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());

		// 1.4.3.2
		//return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
		
		// 3. UploadController의 재구성
		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath,
				file.getOriginalFilename(), file.getBytes()),
				HttpStatus.CREATED);
	}
	
	// 전송된 파일 화면에 표시, @ResponseBody로 byte[] 데이터가 그대로 전송됨을 의미
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		// 입력 받는 파일은 '년/월/일/파일명' 형식
		logger.info("FILE NAME: " + fileName);
		// 파일명 한글 처리 추가
		fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		logger.info("FILE NAME 222 : " + fileName);

		try {

			String formatName = fileName
					.substring(fileName.lastIndexOf(".") + 1);

			MediaType mType = MediaUtils.getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //적절한 MIME타입 지정
				headers.add("Content-Disposition", "attachment; filename=\""
						+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")
						+ "\""); //한글 처리
			}

			// 리턴 결과는 실제로 파일의 데이터가 됨
			// IOUtils 부분이 대상 파일에서 데이터를 읽어내는 부분
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	// 5.1 file 삭제
	/*
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {

		logger.info("delete file: " + fileName);

		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		MediaType mType = MediaUtils.getMediaType(formatName);

		if (mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath	+ (front + end).replace('/', File.separatorChar)).delete();
		}

		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	*/
	
	// 8.1.3 첨부파일삭제 기능 추가, 여러 파일 처리를 위해 배열로 입력 받음
	@ResponseBody
	@RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {

		logger.info("delete all files: " + files);

		if (files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}

		for (String fileName : files) {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			MediaType mType = MediaUtils.getMediaType(formatName);

			if (mType != null) {
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(uploadPath	+ (front + end).replace('/', File.separatorChar)).delete();
			}

			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

}
