package org.mycom.service.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.inject.Inject;

import org.mycom.dao.Ag113DAO;
import org.mycom.domain.Ag113VO;
import org.mycom.util.FssENV;
import org.mycom.util.Utils;
import org.springframework.stereotype.Component;

/*
 * 2016.04.22 AG113 insert 처리
 */

@Component
public class BaseAg113FmtImpl implements BaseFileFmt {
	
	@Inject
	private Ag113DAO dao;

	@Override
	public int process(String str, String up_id) throws Exception {
		
		Ag113VO vo = split(str, up_id);
		
		// Return result count
		return dao.register(vo);
	}
	
	// insert table (for upload)
	public Ag113VO split(String str, String up_id) throws Exception {
		
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
		
		return vo;
	}

	@Override
	public int write(String make_date, String up_id) throws Exception {

		// data select
		List<Ag113VO> list = dao.allColSelect(make_date);
		
		File wfile = new File(FssENV.BaseFileDown, "AG113.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(wfile.getPath(), true));
		
		int rowcnt = 0;
		StringBuffer bf = new StringBuffer();
		
		for(Ag113VO vo : list){
			rowcnt++;
			//System.out.println(">>>"+vo.toString());
			
			bf = bf.delete(0, bf.length());
			bf.append("AG113");
			bf.append(Utils.appendRString(vo.getMake_date(), 8));
			bf.append(Utils.appendRString(vo.getComp_code(), 7));
			bf.append(Utils.appendRString(vo.getIssues_no(), 12));
			bf.append(Utils.appendRString(vo.getTrade_type(), 2));
			bf.append(Utils.appendRString(vo.getTrade_date(), 8));
			bf.append(Utils.appendRString(vo.getComp_name(), 30));
			bf.append(Utils.appendRString(vo.getIssu_name(), 40));
			bf.append(Utils.appendRString(vo.getPublish_code(), 5));
			bf.append(Utils.appendRString(vo.getPublish_rel(), 1));
			bf.append(Utils.appendZero(vo.getOrdr_time(), 6));
			bf.append(Utils.appendZero(vo.getOrdr_qty(), 19));
			bf.append(Utils.appendZero(vo.getOrdr_price(), 19));
			bf.append(Utils.appendZero(vo.getAmd_rtn(), 19));
			bf.append(Utils.appendZero(vo.getAmd_qty(), 19));
			bf.append(Utils.appendRString(vo.getLast_ccl_time(), 6));
			bf.append(Utils.appendZero(vo.getCcl_rtn(), 19));
			bf.append(Utils.appendZero(vo.getCcl_qty(), 19));
			bf.append(Utils.appendRString(vo.getLast_cntr_time(), 6));
			bf.append(Utils.appendZero(vo.getCntr_qty(), 19));
			bf.append(Utils.appendZero(vo.getCntr_price(), 19));
			bf.append(Utils.appendZero(vo.getBook_value(), 19));
			bf.append(Utils.appendRString(vo.getCounterpart(), 20));
			bf.append(Utils.appendRString(vo.getCpart_rel(), 1));
			bf.append(Utils.appendZero(vo.getHolding_qty(), 19));
			bf.append(Utils.appendRString(vo.getMarket_mature(), 1));
			bf.append("\n");
			
			bw.write(bf.toString());
		}
		
		bw.close();
		if(rowcnt == 0){ 
			// data 없으면 delete
			wfile.delete();
		}
		
		return rowcnt;
	}

}
