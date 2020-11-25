package com.weiit.resource.common.enums;

public enum EhcacheTime {

	MIN("分钟", 1), HOUR("小时", 2), DAY("天", 3), ALWAYS("长期", 4);

	private String name;
	private int index;

	private EhcacheTime(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
