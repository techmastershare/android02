package com.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.models.Note;

public class NoteModel {
	private ArrayList<Note> mNoteList = new ArrayList<Note>();
	
	public void add(final Note note) {
		mNoteList.add(note);
	}

	public Note get(final int index) {
		if (index < 0)
			return null;
		return mNoteList.get(index);
	}

	public boolean remove(final Note note) {
		return mNoteList.remove(note);
	}
	
	public int size() {
		return mNoteList.size();
	}
	
	public void clear() {
		mNoteList.clear();
	}
	
	public void sortByName() {
		Collections.sort(mNoteList, new Comparator<Note>() {
			public int compare(Note o1, Note o2) {
				String name1 = o1.getName();
				String name2 = o2.getName();
				return name1.compareToIgnoreCase(name2);
			}
		});
	}
}

