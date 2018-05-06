package org.mycom.service;

import java.util.List;

import org.mycom.domain.CodeVO;

public interface CodeService {
	
	// 공통 코드 가져오기
	public List<CodeVO> codelist(String subject, int types) throws Exception;

}
