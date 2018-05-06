package org.mycom.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	// 별도의 데이터 보관이 필요없기 때문에 static 으로 설계
	public static String uploadFile(String uploadPath, String originalName,
			byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalName;

		// 저장될 경로 계산
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);

		// 원본 파일 저장
		FileCopyUtils.copy(fileData, target);

		// 원본 파일의 확장자를 의미
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		// 이미지 타입 판단
		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;

	}

	private static String makeIcon(String uploadPath, String path,
			String fileName) throws Exception {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// 썸네일 이미지 생성
	private static String makeThumbnail(String uploadPath, String path,
			String fileName) throws Exception {

		// BufferedImage는 메모리상의 이미지를 의미
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

		// 높이를 100으로 만듬
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		// 썸네일 파일은 s_ 로 시작
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 경로 인식을 위해 '/'로 치환
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// 년/월/일에 맞는 폴더 생성
	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		logger.info(datePath);

		return datePath;
	}

	// 년/월/일에 맞는 폴더 생성
	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

}
