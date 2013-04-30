package com.anhvu.activity;

import java.io.InputStream;

public class ItemReader {
      private String readerName;
      private InputStream imagePath;
      
      public ItemReader(String name, InputStream path){
    	  readerName = name;
    	  imagePath = path;
      }

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public InputStream getImagePath() {
		return imagePath;
	}

	public void setImagePath(InputStream imagePath) {
		this.imagePath = imagePath;
	}
      
      
}
