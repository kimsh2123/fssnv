package org.mycom.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mycom.domain.Ag113VO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/*
 * 2016.04.08 PDF view 내용 
 * AbstractPdfView 상속 받아서 구현
 */
public class Ag113PdfBuilder extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get data model which is passed by the spring container
		List<Ag113VO> list = (List<Ag113VO>) model.get("list");
		
		document.add(new Paragraph("AG113 List"));
		
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[]{3.0f, 2.0f, 2.0f, 4.0f, 4.0f, 2.0f, 2.0f });
		table.setSpacingBefore(10);
		
		// define font for table header
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(5);
		
		// write table header
		cell.setPhrase(new Phrase("Num", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Make Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Issue No", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Issue Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Type", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Trade Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Upload Time", font));
		table.addCell(cell);
		
		// write table row data
		for(Ag113VO ag113 : list){
			table.addCell(Integer.toString(ag113.getNum()));
			table.addCell(ag113.getMake_date());
			table.addCell(ag113.getIssues_no());
			table.addCell(ag113.getIssu_name());
			table.addCell(ag113.getTrade_type());
			table.addCell(ag113.getTrade_date());
			table.addCell(String.valueOf(ag113.getUp_date()));
		}
		
		document.add(table);
		
	}

}
