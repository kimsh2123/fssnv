package org.mycom.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/* 
 * 2016.03.15 page 처리 계산
 */

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	private int displayPageNum = 10; //page navi 에 보여줄 갯수
	private PagingVO vo;

	public void setCri(PagingVO vo) {
		this.vo = vo;
	}

	// 가장 중요한 계산
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData();
	}

	private void calcData() {

		endPage = (int) (Math.ceil(vo.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount
				/ (double) vo.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;

		next = endPage * vo.getPerPageNum() >= totalCount ? false : true;

	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public PagingVO getCri() {
		return vo;
	}

	public String makeQuery(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("perPageNum", vo.getPerPageNum())
			.build();

		return uriComponents.toUriString();
		
	}
	
	/*
	public String makeSearch(int page) {

		UriComponents uriComponents = UriComponentsBuilder
			.newInstance()
			.queryParam("page", page)
			.queryParam("perPageNum", vo.getPerPageNum())
			.queryParam("searchType",
					((SearchCriteria) cri).getSearchType())
			.queryParam("keyword", ((SearchCriteria) cri).getKeyword())
			.build();

		return uriComponents.toUriString();
	}
	*/
	
}
