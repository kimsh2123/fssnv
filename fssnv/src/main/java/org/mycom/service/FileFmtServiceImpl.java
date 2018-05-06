package org.mycom.service;

import javax.inject.Inject;

import org.mycom.dao.Ag113DAO;
import org.mycom.domain.Ag113VO;
import org.mycom.util.Utils;
import org.springframework.stereotype.Service;

@Service
public class FileFmtServiceImpl implements FileFmtService {
	
	@Inject
	private Ag113DAO dao;

	@Override
	public int splitAg113(String str, String up_id) throws Exception {
		
		Ag113VO vo = new Ag113VO();
		vo.setMake_date(str.substring(5, 13).trim());
		vo.setComp_code(str.substring(13, 20).trim());
		vo.setIssues_no(str.substring(20, 32).trim());
		vo.setTrade_type(str.substring(32, 34).trim());
		vo.setTrade_date(str.substring(34, 42).trim());
		vo.setComp_name(Utils.e2kR(str.substring(42, 72).trim()));
		vo.setIssu_name(Utils.e2kR(str.substring(72, 112).trim()));
		vo.setPublish_code(str.substring(112, 117).trim());
		vo.setPublish_rel(str.substring(117, 118).trim());
		vo.setOrdr_time(str.substring(118, 124).trim());
		vo.setOrdr_qty(str.substring(124, 143).trim());
		vo.setOrdr_price(str.substring(143, 162).trim());
		vo.setAmd_rtn(str.substring(162, 181).trim());
		vo.setAmd_qty(str.substring(181, 200).trim());
		vo.setLast_ccl_time(str.substring(200, 206).trim());
		vo.setCcl_rtn(str.substring(206, 225).trim());
		vo.setCcl_qty(str.substring(225, 244).trim());
		vo.setLast_cntr_time(str.substring(244, 250).trim());
		vo.setCntr_qty(str.substring(250, 269).trim());
		vo.setCntr_price(str.substring(269, 288).trim());
		vo.setBook_value(str.substring(288, 307).trim());
		vo.setCounterpart(Utils.e2kR(str.substring(307, 327).trim()));
		vo.setCpart_rel(str.substring(327, 328).trim());
		vo.setHolding_qty(str.substring(328, 347).trim());
		vo.setMarket_mature(str.substring(347, 348).trim());
		vo.setUp_id(up_id);
		
		// Return result count
		return dao.register(vo);
	}

}
