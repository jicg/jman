package com.jicg.jman.bean.vo.dtree;

import java.io.Serializable;

/** 复选框设计类*/
public class CheckArr implements Serializable {

	/** 复选框标记*/
	private String type;
	
	/** 复选框是否选中*/
	private String checked;
	
	public CheckArr() {}

	public CheckArr(String type, String checked) {
		this.type = type;
		this.checked = checked;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}


	@Override
	public String toString() {
		return "CheckArr [type=" + type + ", isChecked=" + checked + "]";
	}
	
}