package com.horizon.common.util.bean;

import java.io.Serializable;

public class DwzPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352911308140231894L;

	/**
	   * 
	   */
	public DwzPageBean() {
		this(0);
	}

	/**
	   * 
	   */
	public DwzPageBean(int count) {
		this(count, DEFAULT_PAGE_SIZE);
	}

	/**
	   * 
	   */
	public DwzPageBean(int count, int pageSize) {
		this(count, pageSize, 1);
	}

	/**
	   * 
	   */
	public DwzPageBean(int count, int pageSize, int page) {
		this(count, pageSize, page, null);
	}

	/**
	 * @param count
	 *            :记录总数
	 * @param pageSize
	 *            ：每页记录条数
	 * @param page
	 *            ：当前页数
	 * @param ：排序字段
	 * @param order
	 *            :顺序
	 * @param queryCriterions
	 *            :查询条件
	 */
	public DwzPageBean(int count, int pageSize, int page, String orderField) {
		this.totalCount = count;
		this.orderField = orderField;
		this.setPageSize(pageSize);
		this.setPage(page);
	}

	/**
	 * <P>
	 * Function: getLastElementOnPage
	 * <P>
	 * Description:最后一页
	 * <P>
	 * Others: // 其它说明
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getLastElementOnPage() {
		int endIndex = getPageSize() * getPage();
		if (totalCount > 0) {
			return endIndex > totalCount ? totalCount : endIndex;
		}
		return endIndex;
	}

	/**
	 * <P>
	 * Function: getFirstElementOnPage
	 * <P>
	 * Description: // 函数功能、性能等的描述
	 * <P>
	 * Others: // 其它说明
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getFirstElementOnPage() {
		return (getPageSize() * (getPage() - 1));
	}

	/**
	 * <P>
	 * Function: getPage
	 * <P>
	 * Description: // 函数功能、性能等的描述
	 * <P>
	 * Others: // 其它说明
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getPage() {
		if (this.pageNum >= getPageCount()) {
			this.pageNum = getPageCount();
		}
		return this.pageNum;
	}

	/**
	 * <P>
	 * Function: setPage
	 * <P>
	 * Description: // 函数功能、性能等的描述
	 * <P>
	 * Others: // 其它说明
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @param pageNum
	 *            void
	 */
	public void setPage(int pageNum) {
		if (pageNum < 1) {
			this.pageNum = 1;
		}
		if (pageNum > getPageCount()) {
			this.pageNum = getPageCount();
		}
		if (pageNum >= 1 && pageNum <= getPageCount()) {
			this.pageNum = pageNum;
		}
	}

	/**
	 * <P>
	 * Function: getPageSize
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getPageSize() {
		return numPerPage;
	}

	/**
	 * <P>
	 * Function: setPageSize
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @param pageSize
	 *            void
	 */
	public void setPageSize(int pageSize) {
		if (pageSize != this.numPerPage) {
			if (pageSize > 0) {
				this.numPerPage = pageSize;
			}
			this.setPage(pageNum);
		}
	}

	/**
	 * <P>
	 * Function: getOrderBy
	 * <P>
	 * Description:获取排序字段
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return String
	 */
	public String getOrderField() {
		return orderField;
	}

	/**
	 * <P>
	 * Function: getOrderBy
	 * <P>
	 * Description:获取排序字段
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 */
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	/**
	 * <P>
	 * Function: getPageCount
	 * <P>
	 * Description:获取页数
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getPageCount() {
		float numOfPages = (float) totalCount / getPageSize();
		return (int) ((numOfPages > (int) numOfPages || numOfPages == 0.0) ? numOfPages + 1
				: numOfPages);
	}

	/**
	 * <P>
	 * Function: getPageNumShown
	 * <P>
	 * Description:获取总页数
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getPageNumShown(int totalCount, int numPerPage) {
		pageNumShown = DEFAULT_PAGE_SHOW;
		if (totalCount % numPerPage == 0) {
			pageNumShown = totalCount / numPerPage;
		} else {
			pageNumShown = (totalCount / numPerPage) + 1;
		}
		if (MAX_PAGE_NUM_SHOWN < pageNumShown) {
			pageNumShown = MAX_PAGE_NUM_SHOWN;
		}
		return pageNumShown;
	}

	/**
	 * <P>
	 * Function: getNumPerPage
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/**
	 * <P>
	 * Function: setNumPerPage
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @param numPerPage
	 *            void
	 */
	public void setNumPerPage(int numPerPage) {
		if (DEFAULT_PAGE_SIZE <= numPerPage) {
			this.numPerPage = numPerPage;
		}
	}

	/**
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	    * 
	    */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * <P>
	 * Function: getTotalCount
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * * @author: peiyy
	 * 
	 * @CreateTime: 2012-09-18
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.setPage(pageNum);
	}

	/**
	 * <P>
	 * Function: getPageNumShown
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return int
	 */
	public int getPageNumShown() {
		return this.getPageNumShown(totalCount, numPerPage);
	}

	/**
	 * @author: peiyy
	 * 
	 * @CreateTime: 2012-09-18
	 */
	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	/**
	 * <P>
	 * Function: getOrderDirection
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 * @return String
	 */
	public String getOrderDirection() {
		return orderDirection;
	}

	/**
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-09-18
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	/**
	 * 默认的每页记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * pageList默认大小
	 */
	public static final int DEFAULT_PAGELIST_SIZE = 10;
	/**
	 * 默认显示1页
	 */
	public static final int DEFAULT_PAGE_SHOW = 1;

	/**
	 * 最大页标数字为10
	 */
	public static final int MAX_PAGE_NUM_SHOWN = 10;

	/**
	 * 当前页号
	 */
	private int pageNum = 1;
	/**
	 * 每页记录数
	 */
	private int numPerPage = DEFAULT_PAGE_SIZE;
	/**
	 * 总记录数
	 */
	private int totalCount = 0;
	/**
	 * 总页数
	 */
	private int pageNumShown = DEFAULT_PAGE_SHOW;
	/**
	 * 排序字段
	 */
	private String orderField = null;

	/**
	 * desc or asc
	 */
	private String orderDirection = null;

}
