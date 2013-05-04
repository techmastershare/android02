package com.example.countries.model;

import java.util.ArrayList;
import java.util.List;

public class CountryModel {

	private List<Country> list = new ArrayList<Country>();

	public void add(Country country) {
		list.add(country);
	}

	public Country get(int index) {
		if (index < 0)
			return null;

		return list.get(index);
	}

	public int count() {
		return list.size();
	}

	public void clear() {
		list.clear();
	}
}
