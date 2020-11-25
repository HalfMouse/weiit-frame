package com.weiit.resource.common.enums;

public enum FileType {

	IMG("图片", 1), Video("视频", 2), AUDIO("音频", 3), OTHER("其它", 4);

	private String name;
	private int index;

	private FileType(String name, int index) {
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
