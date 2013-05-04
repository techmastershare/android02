package com.example.countries.model;

import java.util.ArrayList;
import java.util.List;

public class TabModel {
	private List<TabObject> list = new ArrayList<TabObject>();

	public void add(TabObject tab) {
		list.add(tab);
	}

	public TabObject get(int index) {
		if (index < 0)
			return null;

		return list.get(index);
	}

	public int count() {
		return list.size();
	}
}
