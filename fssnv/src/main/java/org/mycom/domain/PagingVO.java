package org.mycom.domain;

/*
 * 2016.03.15 paging 처리 VO
 */

public class PagingVO {

	private int page;
	private int perPageNum;

	public PagingVO() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {

		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}

	// method for MyBatis SQL Mapper
	public int getPageStart() {

		return (this.page - 1) * perPageNum;
	}

	// method for MyBatis SQL Mapper
	public int getPerPageNum() {

		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "PagingVO [page=" + page + ", " + "perPageNum=" + perPageNum + "]";
	}

}
