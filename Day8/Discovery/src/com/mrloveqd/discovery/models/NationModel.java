package com.mrloveqd.discovery.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NationModel {
	private ArrayList<Nation> mNationList = new ArrayList<Nation>();

	public void add(final Nation nation) {
		mNationList.add(nation);
	}

	public Nation get(final int index) {
		if (index < 0)
			return null;
		return mNationList.get(index);
	}

	public boolean remove(final Nation nation) {
		return mNationList.remove(nation);
	}

	public int size() {
		return mNationList.size();
	}
	
	public void clear() {
		mNationList.clear();
	}
	
	public void sortByName() {
		Collections.sort(mNationList, new Comparator<Nation>() {
			public int compare(Nation o1, Nation o2) {
				String name1 = o1.getNationName();
				String name2 = o2.getNationName();
				return name1.compareToIgnoreCase(name2);
			}
		});
	}
}
