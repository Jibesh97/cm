package com.horizon.common.util.bean;

import java.util.ArrayList;
import java.util.List;

public class OptionBean {

	private List<Object[]> optionList = new ArrayList<Object[]>();

	public void createOption(String key, Object value){
		Object [] option = new Object [2];
		option[0] = key;
		option[1] = value;
		optionList.add(option);
	}

	public List<Object[]> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Object[]> optionList) {
		this.optionList = optionList;
	}
	
}
