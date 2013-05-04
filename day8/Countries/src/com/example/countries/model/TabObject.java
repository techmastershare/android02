package com.example.countries.model;

public class TabObject {

	private String title;
	private boolean selected;

	public TabObject(String title, boolean selected) {
		this.title = title;
		this.selected = selected;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
