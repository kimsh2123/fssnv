package org.mycom.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.mycom.domain.Ag113VO;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

/*
 * 2016.04.06 XlsX Download
 */

public class Ag113XlsxBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the spring container
		List<Ag113VO> list = (List<Ag113VO>) model.get("list");
		
		// create a new excel sheet
		XSSFSheet sheet = (XSSFSheet)workbook.createSheet("Ag113");
		sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		XSSFCellStyle style = (XSSFCellStyle)workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(0,52,94)));
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		
		XSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Num");
		header.getCell(0).setCellStyle(style);
		
		header.createCell(1).setCellValue("Make Date");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Issue No");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("Issue Name");
		header.getCell(3).setCellStyle(style);
		
		header.createCell(4).setCellValue("거래구분");
		header.getCell(4).setCellStyle(style);
		
		header.createCell(5).setCellValue("Trade Date");
		header.getCell(5).setCellStyle(style);
		
		header.createCell(6).setCellValue("Upload Time");
		header.getCell(6).setCellStyle(style);
		
		// create data rows
		int rowCnt = 1;
		
		for(Ag113VO ag113 : list){
			XSSFRow aRow = sheet.createRow(rowCnt++);
			aRow.createCell(0).setCellValue(ag113.getNum());
			aRow.createCell(1).setCellValue(ag113.getMake_date());
			aRow.createCell(2).setCellValue(ag113.getIssues_no());
			aRow.createCell(3).setCellValue(ag113.getIssu_name());
			aRow.createCell(4).setCellValue(ag113.getTrade_type());
			aRow.createCell(5).setCellValue(ag113.getTrade_date());
			aRow.createCell(6).setCellValue(ag113.getUp_date());
		}
		
		String filename = "AG113.xlsx";
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
	}

}
