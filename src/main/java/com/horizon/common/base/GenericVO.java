package com.horizon.common.base;

public class GenericVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7965442767101409273L;
   
    /**
     * 
     */
    private Integer fromRows = 0;
    
    /**
     * 
     */
    private Integer toRows = 20;

	public Integer getFromRows() {
		return fromRows;
	}

	public void setFromRows(Integer fromRows) {
		this.fromRows = fromRows;
	}

	public Integer getToRows() {
		return toRows;
	}

	public void setToRows(Integer toRows) {
		this.toRows = toRows;
	}

}
