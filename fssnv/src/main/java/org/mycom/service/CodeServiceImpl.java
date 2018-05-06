package org.mycom.service;

import java.util.List;

import javax.inject.Inject;

import org.mycom.dao.CodeDAO;
import org.mycom.domain.CodeVO;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

	@Inject
	private CodeDAO dao;
	
	@Override
	public List<CodeVO> codelist(String subject, int types) throws Exception {
		return dao.codelist(subject, types);
	}

}
