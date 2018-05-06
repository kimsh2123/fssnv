package org.mycom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.mycom.domain.Ag113VO;
import org.mycom.domain.PageMaker;
import org.mycom.domain.PagingVO;
import org.mycom.service.Ag113Service;
import org.mycom.service.CodeService;
import org.mycom.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/*
 * 2016.03.03 by sung
 * ag113 컨트롤러
 */

@Controller
@RequestMapping("/daily/*")
public class Ag113Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ag113Controller.class);
	
	@Inject
	private Ag113Service service;
	
	@Inject
	private CodeService c_service;
	
	
	// ag113 메인 list 페이지 호출 + paging
	@RequestMapping(value = "/ag113List", method = RequestMethod.GET)
	public void mainPage(@ModelAttribute("pvo") PagingVO pvo
			, Model model
			, @RequestParam(value="make_date", defaultValue="") String make_date) throws Exception {
		
		if(make_date.equals("")){
			make_date = Utils.today(1);
		}
		
		logger.info("ag113List CALL :"+make_date+"..."+pvo.toString());
		
		// ag113_tbl에서 data 전체 list 가져오기
		model.addAttribute("make_date", make_date);
		model.addAttribute("list", service.listPaging(make_date, pvo));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(pvo);
		pageMaker.setTotalCount(service.totalCount(make_date));
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
	// register page call
	@RequestMapping(value = "/ag113Register", method = RequestMethod.GET)
	public void registerGet(Model model) throws Exception{
		
		model.addAttribute("codelist_1", c_service.codelist("ag113", 1));
		model.addAttribute("codelist_2", c_service.codelist("ag113", 2));
		model.addAttribute("codelist_3", c_service.codelist("ag113", 3));
		
		logger.info("ag113Regist get ..."+model.toString());
	}
	
	
	// register 처리
	@RequestMapping(value = "/ag113Register", method = RequestMethod.POST)
	public String registerPost(Ag113VO vo, RedirectAttributes rttr) throws Exception{
		
		logger.info("ag113Regist POST ..."+vo.toString());
		
		service.register(vo);
		
		rttr.addAttribute("make_date", vo.getMake_date()); //for search
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/daily/ag113List";
	}
	
	
	// modify page call + paging
	@RequestMapping(value = "/ag113Modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam(value="num") int num
			, @RequestParam(value="make_date") String make_date
			, Model model
			, @ModelAttribute("pvo") PagingVO pvo) throws Exception {

		logger.info("ag113Modify GET ...>" + num + "," +make_date+","+pvo.toString());

		model.addAttribute("codelist_1", c_service.codelist("ag113", 1));
		model.addAttribute("codelist_2", c_service.codelist("ag113", 2));
		model.addAttribute("codelist_3", c_service.codelist("ag113", 3));
		model.addAttribute(service.listOne(make_date, num));
	}
	
	
	// modify 처리 후 modify 화면으로...
	@RequestMapping(value = "/ag113Modify", method = RequestMethod.POST)
	public void modifyPOST(Ag113VO vo, Model model, PagingVO pvo) throws Exception {
		
		logger.info("ag113Modify POST 1 ..."+vo.toString());
		logger.info("ag113Modify POST 2 ..."+pvo.toString());
		
		service.modify(vo);
		
		model.addAttribute("codelist_1", c_service.codelist("ag113", 1));
		model.addAttribute("codelist_2", c_service.codelist("ag113", 2));
		model.addAttribute("codelist_3", c_service.codelist("ag113", 3));
		model.addAttribute(service.listOne(vo.getMake_date(), vo.getNum()));
		model.addAttribute("pvo", pvo); //for paging
		model.addAttribute("msg", "SUCCESS");
	}
	
	
	// modify 화면에서 delete 처리
	@RequestMapping(value = "/ag113DelOne", method = RequestMethod.POST)
	public String deleteOne(Ag113VO vo
			, RedirectAttributes rttr
			, PagingVO pvo) throws Exception {
		
		logger.info("ag113DelOne POST ..."+vo.getMake_date()+",,,"+pvo.toString());
		
		service.delOne(vo);
		
		rttr.addAttribute("make_date", vo.getMake_date()); //for search
		rttr.addFlashAttribute("pvo", pvo); //for paging
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/daily/ag113List";
	}
	
	
	// 전체 Del All 처리 
	@RequestMapping(value = "/ag113DelAll", method = RequestMethod.GET)
	public String delAll(RedirectAttributes rttr
			,@RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		logger.info("ag113DelAll ...>"+make_date);
		
		int rtn = service.delAll(make_date);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		rttr.addFlashAttribute("count", rtn);
		
		return "redirect:/daily/ag113List?make_date="+make_date;
	}
	
	
	// new popup call GET
	@RequestMapping(value = "/ag113Popup", method = RequestMethod.GET)
	public void popupGet(Model model
			,@RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		logger.info("popupGet ...>"+make_date);
		
		model.addAttribute("make_date", make_date);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// excel download poi 사용 2016.03.30
	@RequestMapping(value = "/ag113XlsDown", method = RequestMethod.GET)
	public ModelAndView downloadXls(@RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		logger.info("ag113XlsDown ...>"+make_date);
		//create data
		List<Ag113VO> list = service.listAll(make_date);
		
		return new ModelAndView("ag113XlsView", "list", list);
	}
	
	// Excel XLSX download poi 사용 2016.04.06
	@RequestMapping(value = "/ag113XlsXDown", method = RequestMethod.GET)
	public ModelAndView downloadXlsX(@RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		logger.info("ag113XlsXDown ...>"+make_date);
		//create data
		List<Ag113VO> list = service.listAll(make_date);
		
		return new ModelAndView("ag113ExcelView", "list", list);
	}
	
	// Excel CSV download 2016.04.08
	@RequestMapping(value = "/ag113CsvDown", method = RequestMethod.GET)
	public void downloadCsv(HttpServletResponse response
			, @RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		logger.info("ag113CsvDown ...>"+make_date);
		//create data
		List<Ag113VO> list = service.listAll(make_date);
		
		String fileName = "AG113.csv";
		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		// create mock data
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", headerValue);
		
		// generate csv data
		ICsvMapWriter mapWrite = new CsvMapWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] header = {"Num","Make Date","Issue No","Issue Name",Utils.e2k("거래구분"),"Trade Date","Upload Time"};
		mapWrite.writeHeader(header); // title
		
		for(Ag113VO ag113 : list){
			Map<String, Object> tmp_map = new HashMap<String, Object>();
			tmp_map.put(header[0], ag113.getNum());
			tmp_map.put(header[1], ag113.getMake_date());
			tmp_map.put(header[2], ag113.getIssues_no());
			tmp_map.put(header[3], Utils.e2k(ag113.getIssu_name()));
			tmp_map.put(header[4], Utils.e2k(ag113.getTrade_type()));
			tmp_map.put(header[5], ag113.getTrade_date());
			tmp_map.put(header[6], ag113.getUp_date());
			mapWrite.write(tmp_map, header);
		}
		mapWrite.close();
	}
	
	// PDF Download 2016.04.08
	@RequestMapping(value = "/ag113PdfDown", method = RequestMethod.GET)
	public ModelAndView downloadPdf(@RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		logger.info("ag113PdfDown ...>"+make_date);
		//create data
		List<Ag113VO> list = service.listAll(make_date);
		
		return new ModelAndView("ag113PdfView", "list", list);
	}
	
}//end of class
